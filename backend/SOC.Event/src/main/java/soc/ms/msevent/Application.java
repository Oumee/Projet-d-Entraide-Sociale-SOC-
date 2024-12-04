package soc.ms.msevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Import;
import soc.ms.msdatamongodb.config.MongoDBConfiguration;
import soc.ms.msdatapostgis.config.PostGisDBConfiguration;

@SpringBootApplication(scanBasePackages = {"soc.ms.msevent", "soc.ms.msdatamongodb", "soc.ms.msdatapostgis"})
@Import({MongoDBConfiguration.class, PostGisDBConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JtsModule jtsModule() {
        // This module will provide a Serializer for geometries
        return new JtsModule();
    }
}
