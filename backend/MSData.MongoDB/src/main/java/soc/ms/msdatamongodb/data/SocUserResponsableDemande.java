package soc.ms.msdatamongodb.data;


import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_to_responsable_demandes")
public class SocUserResponsableDemande {
    @Id
    public String id;

    @Lazy
    @DBRef
    public SocUser user;

    public String text;

    public String name;
    public String description;
    public String contactInfo;
    public String website;
    public LocalDate creationDate;
    public SocCommunityType type;
    public String location;

    @Lazy
    @Builder.Default
    private List<String> documents = new ArrayList<>();

    @Builder.Default
    public LocalDate dateDemande = LocalDate.now();

    @Builder.Default
    SocUserResponsableDemandeStatus status = SocUserResponsableDemandeStatus.PENDING;

    public SocUserResponsableDemande(
            SocUser user,
            String text,
            String name,
            String description,
            String contactInfo,
            String website,
            LocalDate creationDate,
            SocCommunityType type,
            String location,
            List<MultipartFile> files) {
        this.user = user;
        this.text = text;
        this.name = name;
        this.description = description;
        this.contactInfo = contactInfo;
        this.website = website;
        this.creationDate = creationDate;
        this.type = type;
        this.location = location;
        this.documents = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                byte[] fileContent = file.getBytes();
                this.documents.add(new String(fileContent));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
