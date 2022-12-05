package com.stoycho.margarita.repository;

import com.stoycho.margarita.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role findByName(String name);
    void deleteById(Long id);
    Role save(Role role);
}
