package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soc.ms.msdatamongodb.data.SocAdmin;

@Repository
public interface ISocAdminRepository extends MongoRepository<SocAdmin, String> {

}
