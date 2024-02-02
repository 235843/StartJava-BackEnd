package com.example.startjava;

import com.example.startjava.Models.User.User;
import com.example.startjava.Models.User.UserRepository;
import com.example.startjava.Models.User.utils.UserDTO;
import com.example.startjava.Models.User.utils.UsersDTO;
import com.example.startjava.utils.Mapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminCotroller {
    @Autowired
    UserRepository userRepository;

    @GetMapping("admin/getUsers")
    public ResponseEntity<UsersDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(Mapper.userToDTO(user));
        }

        return ResponseEntity.ok(new UsersDTO(userDTOS));
    }

    @PatchMapping("admin/toggleUser")
    public JSONObject toggleUser(@RequestBody UserDTO userDTO) {
        User user = userRepository.getUserByEmail(userDTO.getEmail());
        user.setBlock(!user.isBlock());
        userRepository.save(user);
        return new JSONObject();
    }
}
