package com.stoycho.margarita.serviceInterface;

import com.stoycho.margarita.DTO.UserAccountDTO;
import com.stoycho.margarita.model.User;

import java.util.List;

public interface IUserService {
    UserAccountDTO updateUser(UserAccountDTO userAccountDTO);
    User saveUser(User user);
    void addRoleToUser(String username, String roleName);
    List<User> getUsers();
    UserAccountDTO getUserAccountDTO(String uname);
    User getUser(Long nr);
    User getUser(String uname);
    void deleteUser(Long id);
}
