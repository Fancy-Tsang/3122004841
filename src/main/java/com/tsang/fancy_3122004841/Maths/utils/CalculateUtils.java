package com.tsang.fancy_3122004841.Maths.utils;

import com.tsang.fancy_3122004841.Maths.entity.Fraction;

public class CalculateUtils {

    public static Fraction calculate(Fraction operand1, Fraction operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1.add(operand2);
            case '-':
                return operand1.subtract(operand2);
            case '×':
                return operand1.multiply(operand2);
            case '÷':
                return operand1.divide(operand2);
            default:
                throw new IllegalArgumentException("无效的运算符");
        }
    }
}
