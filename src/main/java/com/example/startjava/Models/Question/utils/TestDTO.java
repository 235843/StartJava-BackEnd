package com.example.startjava.Models.Question.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestDTO {
    public List<QuestionDTO> questions;

    public TestDTO(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
