package com.tsang.fancy_3122004841.Maths.entity;

public class Fraction {

    private int numerator; // 分子
    private int denominator;// 分母

    public Fraction(int numerator, int denominator) {
        if (denominator == 0){
            throw new IllegalArgumentException("分母不能为0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    //计算最大公约数
    private int gcd(int a, int b) {
        return b==0 ? a : gcd(b, a%b);
    }

    //约分
    private void simplify(){
        //为什么要加绝对值？？
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        //为什么分母有可能为负数？？
        if(denominator < 0){
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    //倒数
    public Fraction reciprocal() {
        if (numerator == 0) {
            return this; //返回当前对象
        }
        return new Fraction(denominator, numerator);
    }

    //加法运算，先通分再计算
    public Fraction add(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        return new Fraction(newNumerator, commonDenominator);
    }

    //减法运算，先通分再计算
    public Fraction subtract(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        return new Fraction(newNumerator, commonDenominator);
    }

    //乘法运算
    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    //除法运算
    public Fraction divide(Fraction other) {
        return this.multiply(other.reciprocal());
    }

    public static Fraction parseFraction(String fractionStr) {
        if (fractionStr.contains("'")) {
            String[] parts = fractionStr.split("'");
            int wholePart = Integer.parseInt(parts[0]);
            String fractionalPart = parts[1];
            return parseFraction(fractionalPart).add(new Fraction(wholePart));
        }

        if (fractionStr.contains("/")) {
            String[] parts = fractionStr.split("/");
            int numerator = Integer.parseInt(parts[0]);
            int denominator = Integer.parseInt(parts[1]);
            return new Fraction(numerator, denominator);
        }

        return new Fraction(Integer.parseInt(fractionStr));
    }

    @Override
    public String toString(){
        if (denominator == 1){
            return String.valueOf(numerator);
        }
        if (Math.abs(numerator) > denominator) {
            int wholePart = numerator / denominator;
            int remainder = Math.abs(numerator % denominator);

            if (remainder == 0) {
                return String.valueOf(wholePart);
            }
            return String.format("%d'%d/%d", wholePart, remainder, denominator);
        }
        return numerator + "/" + denominator;
    }

    public boolean isNegative() {
        return numerator < 0;
    }
}
