package com.tsang.fancy_3122004841;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PaperPlagiarismChecker {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: java PaperPlagiarismChecker <original_file_path> <plagiarized_file_path> <output_file_path>");
            return;
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];

        String originalText = new String(Files.readAllBytes(Paths.get(originalFilePath)));
        String plagiarizedText = new String(Files.readAllBytes(Paths.get(plagiarizedFilePath)));

        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        double rate = similarityCalculator.calculateSimilarity(originalText, plagiarizedText);

/*        List<Term> originalTerms = HanLP.segment(originalText);
        List<Term> plagiarizedTerms = HanLP.segment(plagiarizedText);

        // 提取词汇列表
        List<String> originalWords = new ArrayList<>();
        List<String> plagiarizedWords = new ArrayList<>();
        for (Term term : originalTerms) {
            originalWords.add(term.word);
        }
        for (Term term : plagiarizedTerms) {
            plagiarizedWords.add(term.word);
        }

        // 使用HashSet来自动去重并找到交集
        Set<String> originalSet = new HashSet<>(originalWords);
        Set<String> plagiarizedSet = new HashSet<>(plagiarizedWords);
        originalSet.retainAll(plagiarizedSet); // 交集

        // 计算重复率（更精确的方法）
        Set<String> unionSet = new HashSet<>(originalWords);
        unionSet.addAll(plagiarizedWords);
        double precisePlagiarismRate = (double) originalSet.size() / unionSet.size();
*/
//        List<String> originalTokens = Arrays.asList(HanLP.segment(originalText).toArray(new String[0]));
//        List<String> plagiarizedTokens = Arrays.asList(HanLP.segment(plagiarizedText).toArray(new String[0]));

//        Set<String> intersection = new HashSet<>(originalTokens);
//        intersection.retainAll(plagiarizedTokens);
//
//        double repeatedTokens = intersection.size();
//        double totalTokens = originalTokens.size() + plagiarizedTokens.size() - repeatedTokens; // 粗略计算总词数（避免重复计算）

//        double plagiarismRate = (double) repeatedTokens / totalTokens;

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            writer.write(String.format("%.2f", rate));
        }

        System.out.println("Plagiarism rate written to " + outputFilePath);
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
