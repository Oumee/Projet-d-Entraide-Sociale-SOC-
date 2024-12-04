package soc.ms.msdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import soc.ms.msdatamongodb.data.SocUserResponsableDemande;
import soc.ms.msdatamongodb.data.SocUserResponsableDemandeStatus;

import java.util.List;
import java.util.stream.Stream;

public interface ISocUserResponsableDemandeRepository extends MongoRepository<SocUserResponsableDemande, String> {
}
