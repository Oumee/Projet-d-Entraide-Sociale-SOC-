package soc.ms.msdatamongodb.data;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "communities_comments")
public class SocCommunityComment {

    @Id
    public String id;
    public SocUser sender;
    public String content;
    @Builder.Default
    public LocalDateTime creationDate = LocalDateTime.now();
    @Builder.Default
    public LocalDateTime lastUpdateDate = LocalDateTime.now();
    public boolean isDeleted;
    public boolean isPinned;

    @Lazy
    @Builder.Default
    @DBRef
    public List<SocUser> likes = new Vector<>();

    @Lazy
    @Builder.Default
    @DBRef
    public List<SocUser> dislikes = new Vector<>();

    @Lazy
    @Builder.Default
    @DBRef
    public List<SocCommunityComment> replies = new Vector<>();

    public boolean isEdited() {
        return creationDate != lastUpdateDate;
    }
}
