package soc.ms.mscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import soc.ms.msdatamongodb.config.MongoDBConfiguration;
import soc.ms.msdatapostgis.config.PostGisDBConfiguration;

@SpringBootApplication(scanBasePackages = {"soc.ms.mscommunity", "soc.ms.msdatamongodb", "soc.ms.msdatapostgis"})
@Import({MongoDBConfiguration.class, PostGisDBConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
