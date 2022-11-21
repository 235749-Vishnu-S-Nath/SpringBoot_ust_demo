package com.student.ust;

import com.student.ust.entity.User;
import com.student.ust.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    @Test
    public void testCreateUser(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String rawPassword="gilgamesh";
        String encodedPassword= passwordEncoder.encode(rawPassword);

        User newUser=new User("235749@ust.com",encodedPassword);

        User savedUser= repo.save(newUser);

        assert(savedUser).getId()>0;
        assert(savedUser.getEmail()).endsWith(".com");
    }
}