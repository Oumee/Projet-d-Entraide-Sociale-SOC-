package soc.ms.msdatamongodb.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "/soc_notification")
public class SocNotification {
    @Id
    public String id;
    public String communityName;
    public String title;
    public String message;
    public SocNotificationType type;
    @Builder.Default
    public LocalDateTime createdDate = LocalDateTime.now();
    @Builder.Default
    public LocalDateTime updatedDate = LocalDateTime.now();
    public LocalDateTime finalDate;
}
