package com.tsang.fancy_3122004841;

import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class SimilarityCalculatorTest {

    @Test
    void calculateSimilarity() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("hello", "hello");
        assertEquals(1.0, similarity, 0.001);
    }

    @Test
    public void testNoSimilarity() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("hello", "world");
        assertEquals(0.2, similarity, 0.001);
    }

    @Test
    public void testPartialMatch() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("hello", "helicopter");
        assertEquals(0.5333, similarity, 0.001);
    }

    @Test
    public void testSameLengthDifferentContent() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("java", "python");
        assertEquals(0.0, similarity, 0.001);
    }

    @Test
    public void testDifferentLengthWithCommonSubsequence() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abcde", "ace");
        assertEquals(0.75, similarity, 0.001);
    }

    @Test
    public void testEmptyStrings() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("", "");
        assertEquals(NaN, similarity, 0.001);
    }

    @Test
    public void testOneEmptyString() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abc", "");
        assertEquals(0.0, similarity, 0.001);
    }

    @Test
    public void testSameCharactersDifferentOrder() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abc", "cba");
        assertEquals(0.3333, similarity, 0.001);
    }

}