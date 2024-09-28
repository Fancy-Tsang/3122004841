package com.tsang.fancy_3122004841.Maths.utils;

import com.tsang.fancy_3122004841.Maths.entity.Fraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateUtilsTest {

    @Test
    void testAdd() {
        Fraction fraction1 = new Fraction(1);
        Fraction fraction2 = new Fraction(2, 3);

        Fraction fraction3 = new Fraction(5, 3);
        Fraction result = CalculateUtils.calculate(fraction1, fraction2, '+');
        assertEquals(fraction3.toString(), result.toString());
    }
}