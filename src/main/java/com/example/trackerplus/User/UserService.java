package com.example.trackerplus.User;


import com.example.trackerplus.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean checkIfUserExistByUsername(String username){
        return userRepository.getUserByUsername(username) != null;
    }
    public boolean checkIfUserExistByLogin(String login){
        return userRepository.getUserByLogin(login) != null;
    }
    public User registerUser(String username, String login, String password){
        User user = new User();
        user.setUsername(username);
        user.setLogin(login);

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String encodedPassword = passwordGenerator.changePassword(password);

        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);

        userRepository.save(user);
        return userRepository.getUserByLogin(login);
    }
}
