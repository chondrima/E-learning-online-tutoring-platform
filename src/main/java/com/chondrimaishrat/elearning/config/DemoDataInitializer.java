package com.chondrimaishrat.elearning.config;

import com.chondrimaishrat.elearning.user.AccountStatus;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserAccountRepository;
import com.chondrimaishrat.elearning.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataInitializer {

    @Bean
    CommandLineRunner createLocalAdmin(UserAccountRepository users, PasswordEncoder passwords) {
        return arguments -> {
            String email = "admin@learntogether.local";
            if (!users.existsByEmail(email)) {
                users.save(new UserAccount(
                        "Platform Administrator", email, passwords.encode("Admin@123"),
                        UserRole.ADMIN, AccountStatus.ACTIVE
                ));
            }
        };
    }
}
