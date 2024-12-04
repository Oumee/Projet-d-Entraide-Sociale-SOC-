package soc.ms.msuser.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeRequest;
import soc.ms.msclientapi.user.payload.requests.UserResponsableDemandeTypeRequest;
import soc.ms.msclientapi.user.payload.responses.ResponsableDemandeResponse;
import soc.ms.msuser.interfaces.Constants;

import java.util.List;

@RequestMapping(Constants.UserResponsableDemandeHeader)
public interface ISocUserResponsableDemandeController {

    @GetMapping(Constants.UserResponsableDemandeGetAll)
    ResponseEntity<List<ResponsableDemandeResponse>> getAllDemandes(@RequestBody UserResponsableDemandeTypeRequest request);

    @GetMapping(Constants.UserResponsableDemandeGet)
    ResponseEntity<ResponsableDemandeResponse> getDemande(@RequestBody UserResponsableDemandeRequest request);

    @PostMapping(Constants.UserResponsableDemandeAdd)
    ResponseEntity<String> addDemande(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("text") String text,
            @RequestParam("document0") MultipartFile doc0,
            @RequestParam("document1") MultipartFile doc1,
            @RequestParam("document2") MultipartFile doc2,
            @RequestParam("document3") MultipartFile doc3,
            @RequestParam("document4") MultipartFile doc4,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("contactInfo") String contactInfo,
            @RequestParam("website") String website,
            @RequestParam("creationDate") String creationDate,
            @RequestParam("communityType") String communityType,
            @RequestParam("location") String location);

    @PostMapping(Constants.UserResponsableDemandeDeny)
    ResponseEntity<Void> denyDemande(@RequestBody UserResponsableDemandeRequest request);

    @PostMapping(Constants.UserResponsableDemandeAccept)
    ResponseEntity<List<ResponsableDemandeResponse>> acceptDemande(@RequestBody UserResponsableDemandeRequest request);
}
