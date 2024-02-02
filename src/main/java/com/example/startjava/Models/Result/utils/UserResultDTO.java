package com.example.startjava.Models.Result.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResultDTO {
    public List<ResultDTO> results;

    public UserResultDTO(List<ResultDTO> results) {
        this.results = results;
    }
}
