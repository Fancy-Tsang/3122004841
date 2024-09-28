package com.tsang.fancy_3122004841.Maths.utils;

import com.tsang.fancy_3122004841.Maths.entity.Fraction;

import java.util.Random;

public class FractionUtils {

    private static final Random RANDOM = new Random();

    //随机生成真分数
    public static Fraction generateRandomFraction(int range) {
        //分子 0 ~ range-1
        int numerator = RANDOM.nextInt(range);
        //分母 1 ~ range-1
        int denominator = RANDOM.nextInt(range - 1) + 1;
        return new Fraction(numerator, denominator);
    }

    //随机生成运算数
    public static Fraction generateRandomOperand(String operator, int range) {
        if (RANDOM.nextBoolean()) {
            // 真分数
            return generateRandomFraction(range);
        } else {
            // 自然数
            if ("÷".equals(operator)) {
                return new Fraction(RANDOM.nextInt(range - 1) + 1);
            }
            return new Fraction(RANDOM.nextInt(range));
        }
    }
}
