package soc.ms.msdatamongodb.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("soc.ms.msdatamongodb.config")
@EnableMongoRepositories("soc.ms.msdatamongodb.repository")
@EntityScan("soc.ms.msdatamongodb.data")
public class MongoDBConfiguration {
}
