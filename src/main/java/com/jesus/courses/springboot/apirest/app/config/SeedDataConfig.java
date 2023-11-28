package com.jesus.courses.springboot.apirest.app.config;

import com.jesus.courses.springboot.apirest.app.models.entity.Role;
import com.jesus.courses.springboot.apirest.app.models.entity.User;
import com.jesus.courses.springboot.apirest.app.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedDataConfig implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public SeedDataConfig(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userService.count() == 0) {
            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRole(Role.ROLE_ADMIN);

            userService.save(admin);
        }
    }
}
