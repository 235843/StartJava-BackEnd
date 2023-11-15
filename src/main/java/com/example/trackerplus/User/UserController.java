package com.example.trackerplus.User;

import com.nimbusds.jose.shaded.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.nimbusds.jose.shaded.json.JSONObject;


import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/loginBasic")
    @ResponseBody
    public JSONObject tryToLogin(Authentication authentication){
        try{
            JSONObject jsonObject = new JSONObject();
            Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
            jsonObject.put("user_role", auth);
            return jsonObject;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/register")
    @ResponseBody
    public JSONObject registerUser(@RequestBody String data){
        try {
            JSONObject status = new JSONObject();
            JSONParser parser = new JSONParser();
            JSONObject value = (JSONObject) parser.parse(data);
            String name = (String) value.get("username");
            if(userService.checkIfUserExistByUsername(name)){
                throw new Exception("Username already in use");
            }
            String login =(String) value.get("login");
            if(userService.checkIfUserExistByLogin(login)){
                throw new Exception("Login already in use");
            }
            String password = (String) value.get("password");
            User user = userService.registerUser(name, login, password);
            status.put("user", user);
            return status;
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
