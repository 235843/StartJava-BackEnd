package com.example.startjava.Models.User;


import com.example.startjava.utils.PasswordGenerator;
import com.example.startjava.Models.User.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public boolean checkIfUserExistByEmail(String email){
        return userRepository.getUserByEmail(email) != null;
    }
    public User registerUser(String email, String password){
        User user = new User();

        user.setEmail(email);

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String encodedPassword = passwordGenerator.changePassword(password);

        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);
        user.setResults(new ArrayList<>());

        userRepository.save(user);
        return userRepository.getUserByEmail(email);
    }

    public void changePassword(String email, String password){
        User user = userRepository.getUserByEmail(email);
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String encodedPassword = passwordGenerator.changePassword(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
