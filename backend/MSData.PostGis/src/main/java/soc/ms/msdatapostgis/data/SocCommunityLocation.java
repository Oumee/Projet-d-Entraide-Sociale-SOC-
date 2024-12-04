package soc.ms.msdatapostgis.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;

@Builder
@Getter
@Setter
@Entity(name = "soc_community")
@NoArgsConstructor
@AllArgsConstructor
public class SocCommunityLocation {
    @Id
    @Column(name = "community_name")
    public String communityName;

    @Column(name = "location")
    public Coordinate location;
}
