package com.stoycho.margarita.service;

import com.stoycho.margarita.DTO.UserAccountDTO;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.Role;
import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.repository.IRoleRepository;
import com.stoycho.margarita.repository.IUserRepository;
import com.stoycho.margarita.serviceInterface.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Slf4j
@Service
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            IUserRepository iUserRepository,
            IRoleRepository iRoleRepository,
            PasswordEncoder passwordEncoder) {
        this.iUserRepository = iUserRepository;
        this.iRoleRepository = iRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.getUserByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
            authorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return iUserRepository.findAll();
    }

    @Override
    public UserAccountDTO getUserAccountDTO(String uname) {
        log.info("Fetching user with username: {}", uname);
        return new UserAccountDTO(iUserRepository.getUserByUsername(uname));
    }
    @Override
    public User getUser(Long nr) {
        log.info("Fetching user {}", nr);
        return iUserRepository.getUserById(nr);
    }

    @Override
    public User getUser(String uname) {
        log.info("Fetching user {}", uname);
        return iUserRepository.getUserByUsername(uname);
    }

    @Override
    public void deleteUser(Long id) {

        iUserRepository.deleteById(id);
    }
    @Override
    public UserAccountDTO updateUser(UserAccountDTO userAccountDTO){
        User user= iUserRepository.getUserByUsername(userAccountDTO.getUsername());
        user.setName(userAccountDTO.getName());
        user.setEmail(userAccountDTO.getEmail());
        user.setPhone(userAccountDTO.getPhone());
        user.setStreet(userAccountDTO.getStreet());
        user.setCity(userAccountDTO.getCity());
        user.setCountry(userAccountDTO.getCountry());
        user.setStreetNumber(userAccountDTO.getStreetNumber());
        user.setZipCode(userAccountDTO.getZipCode());
        return new UserAccountDTO(user);

    }
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getName());
        if (iUserRepository.checkIfExist(user.getUsername())) {
            throw new BadRequestException(
                    "Username " + user.getUsername() + " taken");
        }
        //Validate Password
        if (!((user.getPassword().length() >= 8)
                && (user.getPassword().length() <= 15)))
            throw new BadRequestException("Password length should be"
                    + " between 8 to 15 characters");
        if (user.getPassword().contains(" ")) {
            throw new BadRequestException("Password should not"
                    + " contain any space");
        }

        //Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Create shopping cart for the user
        user.setShoppingCard(new ShoppingCard(user));
        //Save the user
        return iUserRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = iUserRepository.getUserByUsername(username);
        Role role = iRoleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}