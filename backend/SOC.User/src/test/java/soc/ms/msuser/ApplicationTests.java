package soc.ms.msuser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import soc.ms.msdatapostgis.config.PostGisDBConfiguration;


@SpringBootTest

@Import(PostGisDBConfiguration.class)

class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
