package com.tsang.fancy_3122004841.Maths.utils;

import com.tsang.fancy_3122004841.Maths.entity.Args;

import java.util.Objects;

public class ValidationUtils {
    public static Args validateArgs(String[] args) {
        Args argsObj = new Args();
        String exercisesFileName = null;
        String answerFileName = null;

        //从命令行参数读取 -n 和 -r 参数
        for(int i = 0; i < args.length; i++){
            if(Objects.equals("-n", args[i]) && i+1 < args.length) {
                try{
                    int numberOfQuestions = Integer.parseInt(args[i+1]);
                    argsObj.setNumberOfQuestions(numberOfQuestions);
                }catch (NumberFormatException e) {
                    throw new IllegalArgumentException("题目个数参数不合法：" + args[i+1]);
                }
            } else if (Objects.equals("-e", args[i]) && i+1 < args.length) {
                exercisesFileName = args[i + 1];
            } else if (Objects.equals("-a", args[i]) && i+1 < args.length) {
                answerFileName = args[i + 1];
            }
        }

        //验证 -e 和 -a 参数的存在性？
        if ((exercisesFileName == null && answerFileName != null) || (exercisesFileName != null && answerFileName == null)){
            throw new IllegalArgumentException("如果需要对给定题目文件和答案文件进行校验，则参数 -e 和 -a 必须同时给出");
        }

        //如果 -e 和 -a 参数都存在，校验文件名并进行答案校验
        if (exercisesFileName != null) {
            if(FileUtils.isNotValidTxtName(exercisesFileName)) {
                throw new IllegalArgumentException("题目文件格式不正确：" + exercisesFileName + "，必须为txt文件");
            }
            if(FileUtils.isNotValidTxtName(answerFileName)) {
                throw new IllegalArgumentException("答案文件格式不正确：" + answerFileName + "，必须为txt文件");
            }
            argsObj.setExercisesFileName(exercisesFileName);
            argsObj.setAnswerFileName(answerFileName);
        }
        return argsObj;
    }
}
