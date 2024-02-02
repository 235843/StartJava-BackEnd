package com.example.startjava.Models.Question.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDTO {
    private String task;
    private List<String> answers;

    public QuestionDTO(String task, List<String> answers){
        this.task = task;
        this.answers = answers;
    }
}
