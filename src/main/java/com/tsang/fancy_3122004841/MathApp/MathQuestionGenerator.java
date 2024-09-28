package com.tsang.fancy_3122004841.MathApp;

import java.util.*;

public class MathQuestionGenerator {
    private Random random = new Random();

    // 简化的方法，生成一个题目
    public String generateQuestion(int range) {
        // 示例：生成形如 a + b/c = ? 的题目
        int a = random.nextInt(range) + 1;
        int b = random.nextInt(range - 1) + 1; // 分子小于范围
        int c = random.nextInt(range - 1) + 1; // 分母小于范围且非0
        int d = a + (b * 100 / c) / 100; // 简化计算过程，避免负数

        return String.format("%d + %d/%d = ?", a, b, c) + " Answer: " + d;
    }

    // 生成多个题目
    public List<String> generateQuestions(int count, int range) {
        List<String> questions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            questions.add(generateQuestion(range));
        }
        return questions;
    }
}