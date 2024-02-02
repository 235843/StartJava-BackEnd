package com.example.startjava.Models.Result;

import com.example.startjava.Models.Category.Category;
import com.example.startjava.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Result getResultByCategoryAndUser(Category category, User user);
    List<Result> getAllByUserOrderByCategory(User user);
}
