package com.tsang.fancy_3122004841;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PaperPlagiarismChecker {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java PaperPlagiarismChecker <original_file_path> <plagiarized_file_path> <output_file_path>");
            return;
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];

        FileHandler fileHandler = new FileHandler();
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();

        try {
            //获取文本内容
            String originalText = fileHandler.readFiles(originalFilePath);
            String plagiarizedText = fileHandler.readFiles(plagiarizedFilePath);
            //计算相似度
            double rate = similarityCalculator.calculateSimilarity(originalText, plagiarizedText);
            //将查重率写入文件
            fileHandler.writeFiles(outputFilePath, rate);
            //打印
            System.out.println("Plagiarism rate written to " + outputFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class FileHandler{
    public String readFiles(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void writeFiles(String filePath, double content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(String.format("%.2f", content));
        }
    }
}

class SimilarityCalculator{
    public double calculateSimilarity(String original, String plagiarized) {
        int lcsLength = longestCommonSubsequence(original, plagiarized);
        return (2.0 * lcsLength) / (original.length() + plagiarized.length());
    }

    private int longestCommonSubsequence(String s1, String s2) {
        int[] prev = new int[s2.length() + 1];
        int[] curr = new int[s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            // 交换当前行和前一行
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[s2.length()];
    }
}
