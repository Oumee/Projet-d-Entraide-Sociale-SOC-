package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soc.ms.msdatamongodb.data.SocCommunity;

@Repository
public interface ISocCommunityRepository extends MongoRepository<SocCommunity, String> {
}
