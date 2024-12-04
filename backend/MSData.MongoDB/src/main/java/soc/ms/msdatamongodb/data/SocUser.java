package soc.ms.msdatamongodb.data;

import lombok.*;

import org.springframework.context.annotation.Lazy;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Vector;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class SocUser {
    @Id
    public String email;
    public String password;
    public String fullName;
    public Date birthDate;
    public String phoneNumber;
    public String address;

    @Lazy
    @Builder.Default
    @DBRef
    public List<SocCommunity> communities = new Vector<>();
}
