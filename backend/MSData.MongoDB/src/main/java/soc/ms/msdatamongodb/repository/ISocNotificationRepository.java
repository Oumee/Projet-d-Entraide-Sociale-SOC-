package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soc.ms.msdatamongodb.data.SocNotification;

import java.util.List;

@Repository
public interface ISocNotificationRepository extends MongoRepository<SocNotification, String> {
    List<SocNotification> findByCommunityName(String communityName);
}
