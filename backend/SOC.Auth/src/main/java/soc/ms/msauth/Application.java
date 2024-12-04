package soc.ms.msauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import soc.ms.msdatamongodb.config.MongoDBConfiguration;

@SpringBootApplication(scanBasePackages = {"soc.ms.msauth", "soc.ms.msdatamongodb"})
@Import(MongoDBConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
