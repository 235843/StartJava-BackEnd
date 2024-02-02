package com.example.startjava.Models.Result;

import com.example.startjava.Models.Category.Category;
import com.example.startjava.Models.Category.CategoryRepository;
import com.example.startjava.Models.User.User;
import com.example.startjava.Models.User.UserRepository;
import com.example.startjava.Models.Result.utils.UserResultDTO;
import com.example.startjava.Models.Result.utils.ResultDTO;
import com.example.startjava.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ResultController {
    @Autowired
    public ResultRepository resultRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public CategoryRepository categoryRepository;

    @GetMapping("results")
    public ResponseEntity<UserResultDTO> getUserResults(Authentication authentication){
        User user = userRepository.getUserByEmail(authentication.getName());
        List<Category> categories = categoryRepository.findAll();
        List<Result> results = resultRepository.getAllByUserOrderByCategory(user);

        List<ResultDTO> resultDTOS = new ArrayList<>();

        int i = 0;
        for (Category category : categories) {

            if (!results.isEmpty() && Objects.equals(results.get(i).getCategory().getName(), category.getName())) {
                resultDTOS.add(Mapper.resultToDTO(results.get(i)));
                i += i == results.size() - 1 ? 0 : 1;
            }
            else {
                resultDTOS.add(Mapper.toResultDTO("", "Nie podjęto próby", category.getName()));
            }
        }

        UserResultDTO res = new UserResultDTO(resultDTOS);
        return ResponseEntity.ok(res);
    }
}
