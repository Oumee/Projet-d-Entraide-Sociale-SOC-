package soc.ms.seeders.seeders;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatapostgis.data.SocEvent;
import soc.ms.msdatapostgis.repository.ISocCommunityLocationRepository;
import soc.ms.msdatapostgis.repository.ISocEventRepository;

import java.time.LocalDateTime;

@Component
public class SocEventSeeder  {
    @Autowired
    private ISocEventRepository _eventRepository;

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Autowired
    private ISocCommunityLocationRepository _communityLocationRepository;

    public void fillEventsInCommunities() {
        _eventRepository.deleteAll();

        // get all communities
        var communities = _communityRepository.findAll();
        communities.forEach(community -> {
            var location = _communityLocationRepository.findById(community.getName()).orElseThrow().location;

            int random = (int) (Math.random() * 10);
            for (int i = 0; i < random; i++) {
                var randomDay = (int) (Math.random() * 10) - 5;
                var faker = new Faker();

                // create event
                var event = SocEvent.builder()
                        .name(faker.lorem().word())
                        .description(faker.lorem().sentence())
                        .message(faker.lorem().sentence())
                        .sendDate(LocalDateTime.now().plusDays(randomDay))
                        .scheduleDate(LocalDateTime.now().plusDays(randomDay + 1))
                        .location(location)
                        .communityName(community.getName())
                        .build();
                _eventRepository.save(event);
            }
        });
    }
}
