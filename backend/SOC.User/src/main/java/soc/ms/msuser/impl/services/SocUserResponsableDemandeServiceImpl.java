package soc.ms.msuser.impl.services;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeAddRequest;
import soc.ms.msclientapi.user.payload.responses.ResponsableDemandeResponse;
import soc.ms.msdatamongodb.data.SocCommunity;
import soc.ms.msdatamongodb.data.SocUserResponsableDemande;
import soc.ms.msdatamongodb.data.SocUserResponsableDemandeStatus;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocUserRepository;
import soc.ms.msdatamongodb.repository.ISocUserResponsableDemandeRepository;
import soc.ms.msdatapostgis.data.SocCommunityLocation;
import soc.ms.msdatapostgis.repository.ISocCommunityLocationRepository;
import soc.ms.msuser.interfaces.services.ISocUserResponsableDemandeService;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SocUserResponsableDemandeServiceImpl implements ISocUserResponsableDemandeService {

    @Autowired
    private ISocUserResponsableDemandeRepository _demandeRepository;

    @Autowired
    private ISocUserRepository _userRepository;

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Autowired
    private ISocCommunityLocationRepository _communityLocationRepository;

    @Override
    public Stream<ResponsableDemandeResponse> getAllDemandes(SocUserResponsableDemandeStatus status) {
        return _demandeRepository.findAll().stream()
                .filter(d -> d.getStatus() == status)
                .map(d -> new ResponsableDemandeResponse(d.id, d.getUser().email, d.dateDemande, d.getStatus()));
    }

    @Override
    public ResponsableDemandeResponse getDemande(String id) {
        return _demandeRepository.findById(id)
                .map(d -> new ResponsableDemandeResponse(d.id, d.getUser().email, d.dateDemande, d.getStatus()))
                .orElseThrow();
    }

    @Override
    public String addDemande(UserResponsableDemandeAddRequest request) {
        if (_communityRepository.findById(request.name()).isPresent()) {
            throw new IllegalArgumentException("Community already exists");
        }

        var user = _userRepository.findById(request.userEmail()).orElseThrow();
        var demande = new SocUserResponsableDemande(
                user,
                request.text(),
                request.name(),
                request.description(),
                request.contactInfo(),
                request.website(),
                request.creationDate(),
                request.type(),
                request.location(),
                request.files()
        );
        return _demandeRepository.save(demande).id;
    }

    @Override
    public void denyDemande(String id) {
        var demande = _demandeRepository.findById(id).orElseThrow();
        demande.setStatus(SocUserResponsableDemandeStatus.DENIED);
        _demandeRepository.save(demande);
    }

    @Override
    public void acceptDemande(String id) {
        var demande = _demandeRepository.findById(id).orElseThrow();

        // TODO: Send to SOC.Community a create request
        var community = SocCommunity.builder()
                .name(demande.name)
                .description(demande.description)
                .contact(demande.contactInfo)
                .website(demande.website)
                .type(demande.getType())
                .creationDate(demande.creationDate)
                .users(List.of(demande.getUser()))
                .build();

        var coord = demande.getLocation().split(", ");
        var location = SocCommunityLocation.builder()
                .communityName(demande.name)
                .location(new Coordinate(Double.parseDouble(coord[0]), Double.parseDouble(coord[1])))
                .build();

        _communityRepository.save(community);
        _communityLocationRepository.save(location);

        demande.setStatus(SocUserResponsableDemandeStatus.ACCEPTED);
        _demandeRepository.save(demande);
    }
}
