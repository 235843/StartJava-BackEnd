package com.example.startjava.Models.Question;

import com.example.startjava.Models.Answer.Answer;
import com.example.startjava.Models.Category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Category category;

    private String task;

    @OneToMany
    private List<Answer> answers;

    @OneToOne
    private Answer correctAnswer;
}
