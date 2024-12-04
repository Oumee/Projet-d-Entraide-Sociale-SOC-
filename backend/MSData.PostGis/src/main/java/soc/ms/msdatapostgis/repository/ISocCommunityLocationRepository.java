package soc.ms.msdatapostgis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soc.ms.msdatapostgis.data.SocCommunityLocation;

@Repository
public interface ISocCommunityLocationRepository extends JpaRepository<SocCommunityLocation, String> {
}
