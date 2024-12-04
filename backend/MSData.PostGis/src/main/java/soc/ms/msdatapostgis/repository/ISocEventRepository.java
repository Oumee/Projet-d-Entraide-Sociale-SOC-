package soc.ms.msdatapostgis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soc.ms.msdatapostgis.data.SocEvent;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ISocEventRepository extends JpaRepository<SocEvent, Long> {
    @Query(value = "SELECT * FROM soc_event WHERE ST_DWithin(location, ST_MakePoint(?1, ?2), ?3)", nativeQuery = true)
    List<SocEvent> findNearestEvents(double lat, double lon, double radius);

    @Query(value = "SELECT * FROM soc_event WHERE community_name = ?1", nativeQuery = true)
    List<SocEvent> findEventsByCommunity(String communityName);
}
