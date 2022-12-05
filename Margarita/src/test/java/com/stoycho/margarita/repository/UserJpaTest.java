package com.stoycho.margarita.repository;

import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;


import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@PropertySource(value = "classpath:application.properties")//
@DataJpaTest
class UserJpaTest {

    @Autowired
    private IUserRepository underTest;

    @AfterEach
     void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfChangeUserName() {
    //given
        String name="Stoycho Stoychev";
        String username="admin001";
        User user = new User(
                null,
                name,
                username,"email.abv.bg","0000000000","street","5","9000","Varna","Bulgaria",
                "PasswordForTheTest",
                new ArrayList<>(),
                null
        );
        user.setShoppingCard(new ShoppingCard(user));
        underTest.save(user);
        Optional<User> savedUser = underTest.findByUsername(username);
        if(savedUser.isEmpty()){
            fail();
        }

        // when
        String newName="Anastas Dimitrov";

        underTest.changeName(savedUser.get().getId(),newName);

        Optional<User> savedUserWithChangedName = underTest.findByUsername(username);
        if(savedUserWithChangedName.isEmpty()){
            fail();
        }
        String expected= savedUserWithChangedName.get().getName();

        // then
        assertThat(expected).isEqualTo(newName);
    }
}