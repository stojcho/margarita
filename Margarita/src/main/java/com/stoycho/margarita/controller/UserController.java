package com.stoycho.margarita.controller;

import com.stoycho.margarita.DTO.UserAccountDTO;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.form.RegisterUserForm;
import com.stoycho.margarita.model.RoleToUserForm;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.serviceInterface.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://172.25.16.1:3000")
public class UserController {

    IUserService iUserService;
    @Autowired
    public UserController( IUserService iUserService)
    {
        this.iUserService = iUserService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users;
        users = iUserService.getUsers();
        if(users != null && !users.isEmpty()) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ExceptionHandler(BadRequestException.class)
    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody RegisterUserForm form) {
        User user=new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        log.info("Saving user: "+ user.toString());
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        User savedUser = iUserService.saveUser(user);
        iUserService.addRoleToUser(savedUser.getUsername(),"ROLE_USER");
        return  ResponseEntity.created(uri).body(savedUser);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity deleteUser(@PathVariable Long id) {
//        iUserService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }


    @GetMapping()
    public ResponseEntity<UserAccountDTO> getUserAccountDTO(@RequestParam("username") String uname) {
        UserAccountDTO user = iUserService.getUserAccountDTO(uname);
        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserAccountDTO> updateUser(@RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO userDTO = iUserService.updateUser(userAccountDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/role/addroletouser")
    public ResponseEntity addRoleToUser(@RequestBody RoleToUserForm form){
        iUserService.addRoleToUser(form.getUsername(),form.getRoleName());
        return  ResponseEntity.ok().build();
    }
}