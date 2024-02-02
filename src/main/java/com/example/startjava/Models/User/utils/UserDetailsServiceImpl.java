package com.example.startjava.Models.User.utils;

import com.example.startjava.Models.User.User;
import com.example.startjava.Models.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }
}
