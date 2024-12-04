package soc.ms.seeders.seeders;

import com.github.javafaker.Faker;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soc.ms.msdatamongodb.data.SocCommunity;
import soc.ms.msdatamongodb.data.SocCommunityType;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatapostgis.data.SocCommunityLocation;
import soc.ms.msdatapostgis.repository.ISocCommunityLocationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Component
public class SocCommunitySeeder {
    private final List<Coordinate> _positions = List.of(
            new Coordinate(33.6091, -7.569),
            new Coordinate(33.5971747, -7.5285529),
            new Coordinate(33.5866639, -7.5475325),
            new Coordinate(33.5902485, -7.6959478),
            new Coordinate(33.5910984, -7.67277),
            new Coordinate(33.6067163, -7.6614497),
            new Coordinate(33.6067163, -7.6614497),
            new Coordinate(33.5573511, -7.6887596),
            new Coordinate(33.5682167, -7.6333523),
            new Coordinate(33.5618255, -7.6120326)
    );

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Autowired
    private ISocCommunityLocationRepository _communityLocationRepository;

    public void fillCommunities() {
        _communityLocationRepository.deleteAll();
        _communityRepository.deleteAll();
        saveCommunities();
    }

    private LocalDate getRandomDate(Faker faker) {
        return LocalDate.of(
                faker.number().numberBetween(2000, 2021),
                faker.number().numberBetween(1, 12),
                faker.number().numberBetween(1, 28)
        );
    }

    private void saveCommunities() {
        ArrayList<SocCommunity> communities = new ArrayList<>();

        Faker faker = new Faker();
        communities.add(SocCommunity.builder()
                .name("Community 1")
                .description("Community 1 description")
                .contact("+212 6 12 34 56 78")
                .creationDate(getRandomDate(faker))
                .website("https://www.community1.com")
                .type(SocCommunityType.CommunityCenter)
                .build());

        for (long i = 1; i < _positions.size(); i++) {
            faker = new Faker();
            communities.add(SocCommunity.builder()
                    .name(faker.name().fullName())
                    .description(faker.lorem().sentence())
                    .contact(faker.phoneNumber().phoneNumber())
                    .creationDate(getRandomDate(faker))
                    .website(faker.internet().url())
                    .type(SocCommunityType.values()[faker.number().numberBetween(0, SocCommunityType.values().length)])
                    .build());
        }

        ArrayList<SocCommunityLocation> locations = new ArrayList<>();
        for (int i = 0; i < _positions.size(); i++) {
            var location = SocCommunityLocation.builder()
                    .communityName(communities.get(i).name)
                    .location(_positions.get(i))
                    .build();
            locations.add(location);
        }


        _communityLocationRepository.saveAll(locations);
        _communityRepository.saveAll(communities);
    }
}
