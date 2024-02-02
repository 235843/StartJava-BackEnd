package com.example.startjava.Models.User.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private boolean isBlocked;

    public UserDTO(String email, boolean isBlocked) {
        this.email = email;
        this.isBlocked = isBlocked;
    }
}
