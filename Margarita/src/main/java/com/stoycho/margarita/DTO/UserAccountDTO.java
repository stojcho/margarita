package com.stoycho.margarita.DTO;

import com.stoycho.margarita.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAccountDTO {
    private String username;
    private String name; // full name, e.g., "Joe Smith"
    private String email;
    private String phone;
    private String street;
    private String streetNumber;
    private String zipCode;
    private String city;
    private String country;

    public UserAccountDTO(User user){
        this.name=user.getName();
        this.email=user.getEmail();
        this.phone=user.getPhone();
        this.street=user.getStreet();
        this.streetNumber=user.getStreetNumber();
        this.zipCode=user.getZipCode();
        this.city=user.getCity();
        this.country=user.getCountry();
    }
}
