package com.example.kitchensink.data;

import com.example.kitchensink.model.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(MemberRepository repository) {
        return args -> {
            repository.save(new Member("John Doe", "john.doe@example.com"));
            repository.save(new Member("Jane Doe", "jane.doe@example.com"));
        };
    }
}
