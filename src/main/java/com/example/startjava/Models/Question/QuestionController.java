package com.example.startjava.Models.Question;

import com.example.startjava.Models.Answer.utils.AnswerDTO;
import com.example.startjava.Models.Category.Category;
import com.example.startjava.Models.Question.utils.QuestionDTO;
import com.example.startjava.Models.Result.Result;
import com.example.startjava.Models.User.User;
import com.example.startjava.Models.User.UserRepository;
import com.example.startjava.Models.Question.utils.TestDTO;
import com.example.startjava.Models.Result.ResultRepository;
import com.example.startjava.Models.Result.utils.ResultDTO;
import com.example.startjava.Models.Category.CategoryRepository;
import com.example.startjava.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("test")
    public ResponseEntity<TestDTO> getQuestions(@RequestParam("cat")String cat){
        Category category = categoryRepository.getCategoryByName(cat);
        List<Question> questions = questionRepository.getAllByCategory(category);

        List<QuestionDTO> testQuestions = new ArrayList<>();

        for (Question question : questions){
            testQuestions.add(Mapper.questionToDTO(question));
        }
        TestDTO result = new TestDTO(testQuestions);
        return ResponseEntity.ok(result);
    }

    @PostMapping("result")
    public ResponseEntity<ResultDTO> getResults(Authentication authentication, @RequestParam("cat") String cat,  @RequestBody AnswerDTO answers) {
        Category category = categoryRepository.getCategoryByName(cat);
        List<Question> questions = questionRepository.getAllByCategory(category);
        User user = userRepository.getUserByEmail(authentication.getName());
        Result result = resultRepository.getResultByCategoryAndUser(category, user);

        int questsCount = questions.size();
        int correctAnswersCount = 0;
        for (int i=0; i < questsCount; i++) {
            if (answers.getAnswers().get(i).equals(questions.get(i).getCorrectAnswer().getAnswer()) ) {
                correctAnswersCount++;
            }
        }
        if(result == null){
            result = new Result();
            result.setCategory(category);
            result.setUser(user);
            result.setQuestionsCount(questsCount);
            result.setCorrectAnswers(correctAnswersCount);
            resultRepository.save(result);
        }
        else if(result.getQuestionsCount() != questsCount){
            result.setCorrectAnswers(correctAnswersCount);
            result.setQuestionsCount(questsCount);
            resultRepository.save(result);
        }
        else if (result.getCorrectAnswers() < correctAnswersCount ) {
            result.setCorrectAnswers(correctAnswersCount);
            resultRepository.save(result);
        }

        return ResponseEntity.ok(Mapper.toResultDTO(correctAnswersCount+"/"+questsCount, Math.round((float) correctAnswersCount / questsCount * 100) + "%", ""));
    }
}
