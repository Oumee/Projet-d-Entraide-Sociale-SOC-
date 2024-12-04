package soc.ms.msuser.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msclientapi.user.payload.responses.UserReponse;
import soc.ms.msdatamongodb.data.SocCommunity;
import soc.ms.msdatamongodb.data.SocUser;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocUserRepository;
import soc.ms.msuser.interfaces.services.ISocUserCommunityService;

import java.util.stream.Stream;

@Service
public class SocUserCommunityServiceImpl implements ISocUserCommunityService {

    @Autowired
    private ISocUserRepository _userRepository;

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Override
    public Stream<String> getAllUserEmails() {
        return _userRepository.findAll().stream().map(SocUser::getEmail);
    }

    @Override
    public UserReponse getUser(String userEmail) {
        var user = _userRepository.findById(userEmail);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        var userEntity = user.get();
        return new UserReponse(userEntity.getFullName(), userEntity.getEmail(), userEntity.getBirthDate(), userEntity.getPhoneNumber(), userEntity.getAddress());
    }

    @Override
    public Stream<String> getCommunities(String userEmail) {
        var user = _userRepository.findById(userEmail);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return user
                .get()
                .getCommunities()
                .stream()
                .map(SocCommunity::getName);
    }

    @Override
    public void joinCommunity(String userEmail, String communityName) {
        var user = _userRepository.findById(userEmail);
        var community = _communityRepository.findById(communityName);
        if (user.isEmpty() || community.isEmpty()) {
            throw new IllegalArgumentException("User or community not found");
        }

        var userEntity = user.get();
        userEntity.getCommunities().add(community.get());
        _userRepository.save(userEntity);

        var communityEntity = community.get();
        communityEntity.getUsers().add(userEntity);
        _communityRepository.save(communityEntity);
    }

    @Override
    public void leaveCommunity(String userEmail, String communityName) {
        var user = _userRepository.findById(userEmail);
        var community = _communityRepository.findById(communityName);
        if (user.isEmpty() || community.isEmpty()) {
            throw new IllegalArgumentException("User or community not found");
        }

        var userEntity = user.get();
        userEntity.getCommunities().remove(community.get());
        _userRepository.save(userEntity);

        var communityEntity = community.get();
        communityEntity.getUsers().remove(userEntity);
        _communityRepository.save(communityEntity);
    }
}
