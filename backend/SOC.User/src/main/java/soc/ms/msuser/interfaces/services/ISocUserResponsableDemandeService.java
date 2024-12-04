package soc.ms.msuser.interfaces.services;

import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeAddRequest;
import soc.ms.msclientapi.user.payload.responses.ResponsableDemandeResponse;
import soc.ms.msdatamongodb.data.SocUserResponsableDemandeStatus;

import java.util.stream.Stream;

public interface ISocUserResponsableDemandeService {
    Stream<ResponsableDemandeResponse> getAllDemandes(SocUserResponsableDemandeStatus status);

    ResponsableDemandeResponse getDemande(String id);

    String addDemande(UserResponsableDemandeAddRequest request);

    void denyDemande(String id);

    void acceptDemande(String id);
}
