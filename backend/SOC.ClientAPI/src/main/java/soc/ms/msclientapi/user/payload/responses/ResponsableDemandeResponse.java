package soc.ms.msclientapi.user.payload.responses;

import soc.ms.msdatamongodb.data.SocUserResponsableDemandeStatus;

import java.time.LocalDate;

public record ResponsableDemandeResponse(
        String id,
        String userEmail,
        LocalDate dateDemande,
        SocUserResponsableDemandeStatus status) {
}
