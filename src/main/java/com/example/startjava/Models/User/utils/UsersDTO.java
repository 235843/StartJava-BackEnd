package com.example.startjava.Models.User.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersDTO {
    private List<UserDTO> users;

    public UsersDTO (List<UserDTO> users) {
        this.users = users;
    }
}
