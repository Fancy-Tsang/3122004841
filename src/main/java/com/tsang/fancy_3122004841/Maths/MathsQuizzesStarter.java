package com.tsang.fancy_3122004841.Maths;

import com.tsang.fancy_3122004841.Maths.entity.Args;
import com.tsang.fancy_3122004841.Maths.utils.InspectionUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.tsang.fancy_3122004841.Maths.utils.QuizGenerator.checkExercisesAnswers;
import static com.tsang.fancy_3122004841.Maths.utils.QuizGenerator.generateQuizzes;

public class MathsQuizzesStarter {

    public static void main(String[] args){
        int num = 0;
        try{
            //检验参数
            Args argsObj = InspectionUtils.validateArgs(args);
            Integer numberOfQuestions = argsObj.getNumberOfQuestions();
            num = numberOfQuestions; // 用于下面异常处理
            Integer range = argsObj.getRange();

            // 判题
            checkExercisesAnswers(argsObj.getExercisesFileName(), argsObj.getAnswerFileName());

            // 生成题目
            CompletableFuture<Void> task = CompletableFuture.runAsync(() -> generateQuizzes(numberOfQuestions, range));

            // 5秒超时
            task.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.err.println("数据范围不支持生成" + num + "道题，请调整参数！");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
