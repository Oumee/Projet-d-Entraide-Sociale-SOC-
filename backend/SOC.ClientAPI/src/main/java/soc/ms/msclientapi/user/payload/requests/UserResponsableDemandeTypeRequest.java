package soc.ms.msclientapi.user.payload.requests;

import soc.ms.msdatamongodb.data.SocUserResponsableDemandeStatus;

public record UserResponsableDemandeTypeRequest(
        SocUserResponsableDemandeStatus status) {
}
