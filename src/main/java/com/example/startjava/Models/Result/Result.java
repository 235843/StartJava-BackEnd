package com.example.startjava.Models.Result;

import com.example.startjava.Models.User.User;
import com.example.startjava.Models.Category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(
            name = "user_results",
            joinColumns = @JoinColumn(name = "results_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;

    @OneToOne
    private Category category;

    private int correctAnswers;
    private int questionsCount;
}
