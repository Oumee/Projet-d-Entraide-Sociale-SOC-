package soc.ms.msdatamongodb.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "admins")
public class SocAdmin {
    @Id
    public String id;
    private String name;
    private String email;
}
