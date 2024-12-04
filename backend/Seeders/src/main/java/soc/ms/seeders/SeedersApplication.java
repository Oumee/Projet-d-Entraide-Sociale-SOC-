package soc.ms.seeders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import soc.ms.msdatamongodb.config.MongoDBConfiguration;
import soc.ms.msdatapostgis.config.PostGisDBConfiguration;

@SpringBootApplication(scanBasePackages = {"soc.ms.seeders", "soc.ms.msdatamongodb", "soc.ms.msdatapostgis"})
@Import({MongoDBConfiguration.class, PostGisDBConfiguration.class})
public class SeedersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedersApplication.class, args);
    }

}
