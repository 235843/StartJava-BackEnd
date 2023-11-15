package com.example.trackerplus.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
    User getUserByLogin(String login);
    User getUserByUsername(String username);

}