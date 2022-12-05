package com.stoycho.margarita.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RoleToUserForm {
    @Column(name="username")
    private String username;
    @Column(name="roleName")
    private String roleName;
}