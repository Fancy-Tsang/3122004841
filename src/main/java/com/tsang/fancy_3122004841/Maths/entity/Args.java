package com.tsang.fancy_3122004841.Maths.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Args {

    private Integer numberOfQuestions = 10;

    private Integer range = 10;

    private String exercisesFileName;

    private String answerFileName;
}
