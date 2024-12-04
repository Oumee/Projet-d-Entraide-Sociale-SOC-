package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import soc.ms.msdatamongodb.data.SocImage;

import java.util.List;

@Repository
public interface ISocSnapsRepository extends MongoRepository<SocImage, String> {

    List<SocImage> findAllByUploaderUserEmail(String uploaderUserEmail);
    List<SocImage> findAllByIdIn(List<String> imageIds);
}
