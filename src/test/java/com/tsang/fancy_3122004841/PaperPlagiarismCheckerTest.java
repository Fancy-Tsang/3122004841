package com.tsang.fancy_3122004841;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PaperPlagiarismCheckerTest {

    @Test
    public void testCheckArgumentsWithIncorrectNumberOfArgs() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(outContent);
        System.setOut(capturedOut);

        PaperPlagiarismChecker.checkArguments(new String[]{"arg1"});

        assertEquals("Usage: java PaperPlagiarismChecker <original_file_path> <plagiarized_file_path> <output_file_path>\r\n", outContent.toString());

        // 恢复System.out
        System.setOut(System.out);
    }
}