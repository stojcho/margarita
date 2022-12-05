package com.stoycho.margarita.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserForm {
    private String username;
    private String email;
    private String password;
}
