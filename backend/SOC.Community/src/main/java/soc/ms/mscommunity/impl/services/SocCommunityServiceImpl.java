package soc.ms.mscommunity.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.mscommunity.interfaces.services.ISocCommunityService;
import soc.ms.mscommunity.payload.requests.CommunityInfoRequest;
import soc.ms.mscommunity.payload.responses.CommunityInfoResponse;
import soc.ms.msdatamongodb.data.SocCommunity;
import soc.ms.msdatamongodb.data.SocCommunityType;
import soc.ms.msdatamongodb.data.SocUser;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatapostgis.repository.ISocCommunityLocationRepository;

import java.util.stream.Stream;

@Service
public class SocCommunityServiceImpl implements ISocCommunityService {

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Autowired
    private ISocCommunityLocationRepository _communityLocationRepository;

    @Override
    public Stream<String> getAllCommunities() {
        return _communityRepository.findAll()
                .stream()
                .map(SocCommunity::getName);
    }

    @Override
    public Stream<String> getAllMembers(String communityName) {
        return _communityRepository.findById(communityName)
                .orElseThrow()
                .getUsers()
                .stream()
                .map(SocUser::getEmail);
    }

    @Override
    public CommunityInfoResponse getCommunityInfo(String communityName) {
        SocCommunity community = _communityRepository.findById(communityName)
                .orElseThrow();
        var location = _communityLocationRepository.findById(communityName)
                .orElseThrow().location;
        return new CommunityInfoResponse(
                community.getName(),
                community.getDescription(),
                community.getType().name(),
                community.getContact(),
                community.getWebsite(),
                community.getCreationDate(),
                location,
                community.getUsers().size()
        );
    }

    @Override
    public void setCommunityInfo(CommunityInfoRequest communityInfo) {
        var community = _communityRepository.findById(communityInfo.communityName())
                .orElseThrow();

        var location = _communityLocationRepository.findById(communityInfo.communityName())
                .orElseThrow();

        location.location = communityInfo.location();

        community.setDescription(communityInfo.communityDescription());
        community.setContact(communityInfo.contactInfo());
        community.setWebsite(communityInfo.website());
        community.setType(SocCommunityType.valueOf(communityInfo.communityType()));
        community.setCreationDate(communityInfo.creationDate());

        _communityRepository.save(community);
        _communityLocationRepository.save(location);
    }
}



