package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import soc.ms.msdatamongodb.data.SocCommunityComment;

public interface ISocCommunityCommentRepository extends MongoRepository<SocCommunityComment, String> {
}