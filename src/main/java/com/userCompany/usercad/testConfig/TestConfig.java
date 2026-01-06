package com.userCompany.usercad.testConfig;


import com.userCompany.usercad.model.User;
import com.userCompany.usercad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Guilherme Silva", "guilherme@gmail.com", "12345");
        User user2 = new User("Maria Oliveira", "maria.oliveira@gmail.com", "abc123");
        User user3 = new User("João Pereira", "joao.pereira@gmail.com", "senha123");
        User user4 = new User("Ana Costa", "ana.costa@gmail.com", "pass456");
        User user5 = new User("Lucas Fernandez", "lucas.fernandes@gmail.com", "lucas789");

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
