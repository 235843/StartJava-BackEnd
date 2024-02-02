package com.example.startjava.Models.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);
}
