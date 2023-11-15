package com.example.trackerplus;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MainController {
    @GetMapping("/home")
    String helloUser(Authentication authentication){
        Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
        System.out.println(auth);
        return "Hello";
    }

    @GetMapping("/admin/home")
    String helloAdmin(){
        return "Hello admin";
    }
}
