package com.stoycho.margarita.repository;

import com.stoycho.margarita.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository  extends JpaRepository<User,Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User SET name=?2 WHERE id=?1")
    void changeName(Long id,String name);
    List<User> findAll();
    User getUserById(Long nr);
    User getUserByUsername(String uname);
    void deleteById(Long urNr);
    User save(User user);

    @Query("select count(p) = 1 from User p where username = ?1")
    boolean checkIfExist(String username);

    Optional<User> findByUsername(String username);
}
