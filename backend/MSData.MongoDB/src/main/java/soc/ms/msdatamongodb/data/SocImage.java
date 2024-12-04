package soc.ms.msdatamongodb.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Snaps")
public class SocImage {

    @Id
    private String id;

    private String uploaderUserEmail;
    private String communityName;

    private String label;

    private Long size;

    private String path;

    private String data;

    private String type;

    private LocalDateTime uploadDate;

    private LocalDateTime lastUpdatedDate;

    public SocImage(String uploaderUserEmail, String communityName, MultipartFile file) {
        this.uploaderUserEmail = uploaderUserEmail;
        this.communityName = communityName;

        this.path = file.getOriginalFilename();
        this.label = getLabel(file);

        this.type = file.getContentType();
        this.size = file.getSize();

        this.size = file.getSize();
        this.uploadDate = LocalDateTime.now();
        this.lastUpdatedDate = LocalDateTime.now();

        try {
            byte[] fileContent = file.getBytes();
            this.data = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLabel(MultipartFile file) {
        String path = file.getOriginalFilename();
        if (path == null) {
            return "<invalid label>";
        }
        return (path.lastIndexOf(".") != -1) ? path.substring(0, path.lastIndexOf(".")) : path;
    }
}
