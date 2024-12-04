package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soc.ms.msdatamongodb.data.SocUser;

import java.util.Optional;

@Repository
public interface ISocUserRepository extends MongoRepository<SocUser, String> {
    Optional<SocUser> findByPhoneNumber(String phoneNumber);
}
