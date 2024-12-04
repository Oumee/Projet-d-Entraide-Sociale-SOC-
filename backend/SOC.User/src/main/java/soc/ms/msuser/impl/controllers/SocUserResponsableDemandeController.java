package soc.ms.msuser.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeAddRequest;
import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeRequest;
import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeTypeRequest;
import soc.ms.msclientapi.user.payload.responses.ResponsableDemandeResponse;
import soc.ms.msdatamongodb.data.SocCommunityType;
import soc.ms.msuser.interfaces.controllers.ISocUserResponsableDemandeController;
import soc.ms.msuser.interfaces.services.ISocUserResponsableDemandeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocUserResponsableDemandeController implements ISocUserResponsableDemandeController {

    @Autowired
    ISocUserResponsableDemandeService _userResponsableDemandeService;

    @Override
    public ResponseEntity<List<ResponsableDemandeResponse>> getAllDemandes(@RequestBody UserResponsableDemandeTypeRequest request) {
        return ResponseEntity.ok(_userResponsableDemandeService.getAllDemandes(request.status()).toList());
    }

    @Override
    public ResponseEntity<ResponsableDemandeResponse> getDemande(@RequestBody UserResponsableDemandeRequest request) {
        return ResponseEntity.ok(_userResponsableDemandeService.getDemande(request.demandeId()));
    }

    @Override
    public ResponseEntity<String> addDemande(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("text") String text,
            @RequestParam(value = "document0", required = true) MultipartFile doc0,
            @RequestParam(value = "document1", required = false) MultipartFile doc1,
            @RequestParam(value = "document2", required = false) MultipartFile doc2,
            @RequestParam(value = "document3", required = false) MultipartFile doc3,
            @RequestParam(value = "document4", required = false) MultipartFile doc4,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("contactInfo") String contactInfo,
            @RequestParam("website") String website,
            @RequestParam("creationDate") String creationDate,
            @RequestParam("type") String type,
            @RequestParam("location") String location) {
        var files = Stream.of(doc0, doc1, doc2, doc3, doc4).filter(Objects::nonNull).toList();
        var request = new UserResponsableDemandeAddRequest(
                userEmail,
                text,
                files,
                name,
                description,
                contactInfo,
                website,
                LocalDate.parse(creationDate),
                SocCommunityType.valueOf(type),
                location);
        return ResponseEntity.ok(_userResponsableDemandeService.addDemande(request));
    }

    @Override
    public ResponseEntity<Void> denyDemande(@RequestBody UserResponsableDemandeRequest request) {
        _userResponsableDemandeService.denyDemande(request.demandeId());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ResponsableDemandeResponse>> acceptDemande(@RequestBody UserResponsableDemandeRequest request) {
        _userResponsableDemandeService.acceptDemande(request.demandeId());
        return ResponseEntity.ok().build();
    }
}
