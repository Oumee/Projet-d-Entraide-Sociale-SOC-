package soc.ms.mscommunity.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.mscommunity.interfaces.services.ISocCommunityCommentService;
import soc.ms.mscommunity.payload.requests.CommunityCommentAddRequest;
import soc.ms.mscommunity.payload.requests.CommunityCommentReplyRequest;
import soc.ms.mscommunity.payload.responses.CommunityCommentFlatResponse;
import soc.ms.mscommunity.payload.responses.CommunityCommentResponse;
import soc.ms.msdatamongodb.data.SocCommunityComment;
import soc.ms.msdatamongodb.data.SocUser;
import soc.ms.msdatamongodb.repository.ISocCommunityCommentRepository;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocUserRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
public class SocCommunityCommentServiceImpl implements ISocCommunityCommentService {

    @Autowired
    private ISocUserRepository _userRepository;

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Autowired
    private ISocCommunityCommentRepository _commentRepository;

    @Override
    public Stream<CommunityCommentResponse> getComments(String communityName) {
        var community = _communityRepository.findById(communityName).orElseThrow();
        return community.getComments()
                .stream()
                .map(SocCommunityCommentServiceImpl::mapToResponse)
                .sorted((a, b) -> Boolean.compare(a.isPinned(), b.isPinned()));
    }

    @Override
    public Stream<CommunityCommentFlatResponse> getFlatComments(String communityName) {
        var community = _communityRepository.findById(communityName).orElseThrow();
        return community.getComments()
                .stream()
                .map(SocCommunityCommentServiceImpl::flatMapToResponse)
                .sorted((a, b) -> Boolean.compare(a.isPinned(), b.isPinned()));
    }

    @Override
    public CommunityCommentResponse getComment(String commentId) {
        return mapToResponse(_commentRepository.findById(commentId).orElseThrow());
    }

    @Override
    public CommunityCommentFlatResponse getFlatComment(String commentId) {
        return flatMapToResponse(_commentRepository.findById(commentId).orElseThrow());
    }

    @Override
    public String addComment(CommunityCommentAddRequest request) {
        return addComment(request.communityName(), request.senderEmail(), request.content(), null);
    }

    @Override
    public void pinComment(String commentId, boolean pin) {
        var comment = _commentRepository.findById(commentId).orElseThrow();
        comment.isPinned = pin;
        _commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        var comment = _commentRepository.findById(commentId).orElseThrow();
        comment.isDeleted = true;
        _commentRepository.save(comment);
    }

    @Override
    public void changeComment(String commentId, String newComment) {
        var comment = _commentRepository.findById(commentId).orElseThrow();
        comment.content = newComment;
        comment.lastUpdateDate = LocalDateTime.now();
        _commentRepository.save(comment);
    }

    @Override
    public void reactComment(String commentId, String userEmail, boolean like, boolean add) {
        var user = _userRepository.findById(userEmail).orElseThrow();
        var comment = _commentRepository.findById(commentId).orElseThrow();
        var list = like ? comment.likes : comment.dislikes;

        if (add) {
            list.add(user);
        } else {
            list.remove(user);
        }
        _commentRepository.save(comment);
    }

    @Override
    public String replyToComment(CommunityCommentReplyRequest reply) {
        return addComment(reply.communityName(), reply.senderEmail(), reply.content(), reply.parentCommentId());
    }

    //

    private String addComment(String communityName, String senderEmail, String content, String parentCommentId) {
        var user = _userRepository.findById(senderEmail).orElseThrow();
        var community = _communityRepository.findById(communityName).orElseThrow();

        var comment = _commentRepository.save(
                SocCommunityComment
                        .builder()
                        .sender(user)
                        .content(content)
                        .build());

        if (parentCommentId != null) {
            var parentComment = _commentRepository.findById(parentCommentId).orElseThrow();
            parentComment.replies.add(comment);
            _commentRepository.save(parentComment);
        }

        community.comments.add(comment);
        _communityRepository.save(community);

        return comment.id;
    }

    private static CommunityCommentResponse mapToResponse(SocCommunityComment comment) {
        return new CommunityCommentResponse(
                comment.id,
                comment.sender.getEmail(),
                comment.content,
                comment.creationDate,
                comment.lastUpdateDate,
                comment.isDeleted,
                comment.isPinned,
                comment.likes.stream().map(SocUser::getEmail).toList(),
                comment.dislikes.stream().map(SocUser::getEmail).toList(),
                comment.replies.stream().map(SocCommunityComment::getId).toList()
        );
    }

    private static CommunityCommentFlatResponse flatMapToResponse(SocCommunityComment comment) {
        return new CommunityCommentFlatResponse(
                comment.id,
                comment.sender.getEmail(),
                comment.content,
                comment.creationDate,
                comment.lastUpdateDate,
                comment.isDeleted,
                comment.isPinned,
                comment.likes.stream().map(SocUser::getEmail).toList(),
                comment.dislikes.stream().map(SocUser::getEmail).toList(),
                comment.replies.stream().map(SocCommunityCommentServiceImpl::flatMapToResponse).toList()
        );
    }
}
