package soc.ms.msdatamongodb.data;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Vector;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "/soc_notification_user")
public class SocNotificationUser {
    @Id
    public String id;
    @Builder.Default
    @Lazy
    public List<String> notificationIds = new Vector<>();
}
