package soc.ms.msdatamongodb.data;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "communities")
public class SocCommunity {
    @Id
    public String name;
    public String description;
    public String contact;
    public String website;
    public LocalDate creationDate;
    public SocCommunityType type;

    @Field("image_ids")
    @Builder.Default
    private List<String> imageIds = new Vector<>();

    @Lazy
    @Builder.Default
    @DocumentReference
    public List<SocUser> users = new Vector<>();

    @Field("subscribed_users")
    @Builder.Default
    private List<SocUser> subscribedUsers = new Vector<>();

    @Lazy
    @Builder.Default
    @DBRef
    public List<SocCommunityComment> comments = new Vector<>();
}
