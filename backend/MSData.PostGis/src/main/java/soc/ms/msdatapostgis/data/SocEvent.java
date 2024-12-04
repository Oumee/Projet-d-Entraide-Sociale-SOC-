package soc.ms.msdatapostgis.data;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity(name = "soc_event")
@NoArgsConstructor
@AllArgsConstructor
public class SocEvent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "location")
    public Coordinate location;

    @Column(name = "community_name")
    public String communityName;

    @Column(name = "name")
    public String name;

    @Column(name = "message")
    public String message;

    @Column(name = "description")
    public String description;

    @Column(name = "send_date")
    public LocalDateTime sendDate;

    @Column(name = "schedule_date")
    public LocalDateTime scheduleDate;
}
