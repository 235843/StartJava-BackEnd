package com.example.startjava.utils;

import com.example.startjava.Models.Answer.Answer;
import com.example.startjava.Models.Question.Question;
import com.example.startjava.Models.Question.utils.QuestionDTO;
import com.example.startjava.Models.Result.Result;
import com.example.startjava.Models.User.User;
import com.example.startjava.Models.User.utils.UserDTO;
import com.example.startjava.Models.Result.utils.ResultDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {
    public static QuestionDTO questionToDTO(Question question){
        List<String> answers = question.getAnswers().stream().map(Answer::getAnswer).collect(toList());
        return new QuestionDTO(question.getTask(), answers);
    }

    public static ResultDTO resultToDTO (Result result) {
        return new ResultDTO(result.getCorrectAnswers() + "/" + result.getQuestionsCount(), Math.round((float)result.getCorrectAnswers() / result.getQuestionsCount() * 100) + "%", result.getCategory().getName());
    }

    public static ResultDTO toResultDTO(String score, String percScore, String category) {
        return new ResultDTO(score, percScore, category);
    }

    public static UserDTO userToDTO(User user) {
        return new UserDTO(user.getEmail(), user.isBlock());
    }
}
