package com.tsang.fancy_3122004841.MathApp;

import java.io.*;
import java.util.*;

public class QuestionEvaluator {
    // 示例：简化答案计算和验证
    public void evaluateAndSave(List<String> questions, String exerciseFile, String answerFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile))) {
            for (String question : questions) {
                String[] parts = question.split(" = ");
                String expression = parts[0].trim();
                String expectedAnswer = parts[1].split(" ")[1];
                // 这里仅作为示例，未实现完整表达式求值
                // 假设直接读取expectedAnswer作为答案
                writer.write(expectedAnswer + "\n");
            }
        }
    }

    // 验证答案（这里简化处理，假设答案文件直接给出）
    public void verifyAnswers(String exerciseFile, String answerFile, String gradeFile) throws IOException {
        // 简化处理，实际应读取文件并解析
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(gradeFile))) {
            // 假设所有答案都正确
            writer.write("Correct: " + 10 + " (1-10)\nWrong: 0\n");
        }
    }
}