package soc.ms.seeders.seeders;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import soc.ms.msdatamongodb.data.SocUser;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocUserSeeder {

    @Value("${seeders.user.count}")
    private long _userCount;

    @Autowired
    private ISocUserRepository _userRepository;

    public void fillUsers() {
        _userRepository.deleteAll();
        _userRepository.saveAll(createUsers());
    }

    private List<SocUser> createUsers() {
        ArrayList<SocUser> users = new ArrayList<>();

        Faker faker = new Faker();
        users.add(SocUser.builder()
                .fullName(faker.name().fullName())
                .email("01013939@gmail.com")
                .password("01013939")
                .birthDate(faker.date().birthday())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .address(faker.address().fullAddress())
                .build());

        for (long i = 0; i < _userCount; i++) {
            faker = new Faker();
            users.add(SocUser.builder()
                    .fullName(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password(faker.internet().password())
                    .birthDate(faker.date().birthday())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .address(faker.address().fullAddress())
                    .build());
        }
        return users;
    }
}
