package soc.ms.mssnaps.impl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import soc.ms.msdatamongodb.data.SocImage;
import soc.ms.msdatamongodb.repository.ISocSnapsRepository;

import java.time.LocalDateTime;
import java.util.Date;

@DataMongoTest

public class ISocSnapsRepositoryTest {
    @Autowired
    private ISocSnapsRepository iSocSnapsRepository;
    SocImage snap;
    @BeforeEach
    void SetUp()
    {
      snap = new SocImage("idrc56755","test@gmail.com","SOS","test.png", 54L,"C:\\Users\\gx178\\Pictures\\Screenshot 2023-08-07 123649.png","fileencodedto64","png", LocalDateTime.now(), LocalDateTime.now());
      iSocSnapsRepository.save(snap);
    }
    @AfterEach
    void tearDown() {
        snap = null;
        iSocSnapsRepository.deleteAll();
    }
}
