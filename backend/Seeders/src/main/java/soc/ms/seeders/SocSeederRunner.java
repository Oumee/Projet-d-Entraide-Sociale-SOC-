package soc.ms.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soc.ms.seeders.seeders.SocCommunitySeeder;
import soc.ms.seeders.seeders.SocEventSeeder;
import soc.ms.seeders.seeders.SocUserSeeder;

@Component
public class SocSeederRunner implements CommandLineRunner {
    @Autowired
    private SocCommunitySeeder _communitySeeder;

    @Autowired
    private SocUserSeeder _userSeeder;

    @Autowired
    private SocEventSeeder _eventSeeder;

    @Override
    public void run(String... args) throws Exception {
        _userSeeder.fillUsers();
        _communitySeeder.fillCommunities();
        _eventSeeder.fillEventsInCommunities();
    }
}
