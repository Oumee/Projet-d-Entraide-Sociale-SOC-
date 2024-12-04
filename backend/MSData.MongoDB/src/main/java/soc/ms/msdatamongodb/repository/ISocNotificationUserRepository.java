package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soc.ms.msdatamongodb.data.SocNotificationUser;

@Repository
public interface ISocNotificationUserRepository extends MongoRepository<SocNotificationUser, String> {
}
