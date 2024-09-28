package com.tsang.fancy_3122004841.MathApp;

import java.io.IOException;
import java.util.List;

public class MathApp {
    public static void main(String[] args) {
        // 解析命令行参数
        // 示例代码，未完整实现
        if (args.length < 2) {
            System.out.println("Usage: Myapp.exe -n <count> -r <range>");
            return;
        }

        int count = 10, range = 10;
        for (String arg : args) {
            if (arg.startsWith("-n")) {
                //1.使用 -n 参数控制生成题目的个数
                count = Integer.parseInt(arg.substring(2));
            } else if (arg.startsWith("-r")) {
                //2.使用 -r 参数控制题目中数值
                range = Integer.parseInt(arg.substring(2));
            }
        }

        //3.将生成10以内（不包括10）的四则运算题目
        MathQuestionGenerator generator = new MathQuestionGenerator();
        List<String> questions = generator.generateQuestions(count, range);

        // 示例：将题目写入文件
        // 这里需要实际文件操作

        QuestionEvaluator evaluator = new QuestionEvaluator();
        try {
            //生成的题目存入执行程序的当前目录下的Exercises.txt文件
            //在生成题目的同时，计算出所有题目的答案，并存入执行程序的当前目录下的Answers.txt文件
            evaluator.evaluateAndSave(questions, "Exercises.txt", "Answers.txt");
            //程序支持对给定的题目文件和答案文件，判定答案中的对错并进行数量统计；统计结果输出到文件Grade.txt
            evaluator.verifyAnswers("Exercises.txt", "Answers.txt", "Grade.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
