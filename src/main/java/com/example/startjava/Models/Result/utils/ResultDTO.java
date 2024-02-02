package com.example.startjava.Models.Result.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO {
    private String score;
    private String percScore;
    private String category;

    public  ResultDTO(String score, String percScore, String category) {
        this.percScore = percScore;
        this.score = score;
        this.category = category;
    }

    public ResultDTO() {

    }
}
