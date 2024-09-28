package com.tsang.fancy_3122004841.Maths.utils;

import cn.hutool.core.io.FileUtil;
import com.tsang.fancy_3122004841.Maths.entity.Fraction;
import groovy.lang.Tuple2;
import io.micrometer.common.util.StringUtils;

import java.io.File;
import java.util.*;

public class QuizGenerator {

    private static final String[] OPERATORS = {"+", "-", "×", "÷"};
    private static final Random RANDOM = new Random();

    public static String generateRandomOperator() {
        return OPERATORS[RANDOM.nextInt(OPERATORS.length)];
    }

    public static int generateRandomOperatorCounts() {
        return RANDOM.nextInt(3) + 1;
    }

    public static Tuple2<List<String>, List<String>> generateQuiz(int range, int numberOfQuestions) {
        int duplicateCount = 0;
        int negativeCount = 0;
        int totalCount = 0;

        List<String> quizzes = new ArrayList<>(numberOfQuestions);
        List<String> answers = new ArrayList<>(numberOfQuestions);

        for(int i = 1; i <= numberOfQuestions; i++) {
            int maxOperators = generateRandomOperatorCounts();
            while(true) {
                totalCount++;
                List<String> operands = new ArrayList<>();
                List<String> operators = new ArrayList<>();

                for (int j = 0; j < maxOperators + 1; j++){
                    String lastOperator = operators.isEmpty() ? "" : operators.get(operators.size() - 1);
                    Fraction operand = FractionUtils.generateRandomOperand(lastOperator, range);
                    operands.add(operand.toString());
                    if (j < maxOperators) {
                        operators.add(generateRandomOperator());
                    }
                }

                StringBuilder quiz = new StringBuilder();
                for (int j = 0; j < operands.size(); j++) {
                    quiz.append(operands.get(j));
                    if (j < operators.size()) {
                        quiz.append(" ").append(operators.get(j)).append(" ");
                    }
                }
                String expression = quiz.toString();

                if (operands.size() > 2 && RANDOM.nextBoolean()) {
                    expression = addRandomParentheses(expression);
                }

                String postfix = ExpressionUtils.infixToPostfix(expression);
                Fraction result = ExpressionUtils.evaluatePostfix(postfix);
                if (Objects.nonNull(result)) {
                    if (!DuplicateChecker.isDuplicate(result, expression)) {
                        quizzes.add(i + ". " + expression);
                        answers.add(i + ". " + result);
                        break;
                    } else {
                        duplicateCount++;
                    }
                } else {
                    negativeCount++;
                }
            }
        }
        System.out.println( numberOfQuestions + "道题目已生成，生成题目总次数：" + totalCount + "，重复次数（已去除该题目）：" + duplicateCount + "，负数次数（已去除该题目）：" + negativeCount);
        return new Tuple2<>(quizzes, answers);
    }

    public static void generateQuizzes(int numberOfQuestions, int range) {
        System.out.printf("生成题目的个数：" + numberOfQuestions + "，题目中数值的范围：0~%d（不包含%d）\n", range, range);

        Tuple2<List<String>, List<String>> quizAndAnswers = QuizGenerator.generateQuiz(range, numberOfQuestions);
        //当前用户目录("user.dir")（即工程根目录）
        String generateExercisesFilePath = System.getProperty("user.dir") + "/Exercises.txt";
        String generateAnswerFilePath = System.getProperty("user.dir") + "/Answers.txt";

        // 删除文件
        File exercisesFile = new File(generateExercisesFilePath);
        File answerFile = new File(generateAnswerFilePath);

        FileUtils.deleteFileIfExists(exercisesFile.getName());
        FileUtils.deleteFileIfExists(answerFile.getName());

        // 将题目和答案写入文件
        FileUtil.writeUtf8Lines(quizAndAnswers.getFirst(), exercisesFile);
        FileUtil.writeUtf8Lines(quizAndAnswers.getSecond(), answerFile);

        System.out.println("新生成的题目问题存入执行程序的当前目录下的Exercises.txt文件，路径如下：" + generateExercisesFilePath);
        System.out.println("新生成的题目答案存入执行程序的当前目录下的Exercises.txt文件，路径如下：" + generateAnswerFilePath);

    }
    private static String addRandomParentheses(String expression) {
        String[] tokens = expression.split(" ");
        StringBuilder result = new StringBuilder();
        List<Integer> addSubIndices = new ArrayList<>();

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            if ("+".equals(operator) || "-".equals(operator)) {
                addSubIndices.add(i);
            }
        }

        if (!addSubIndices.isEmpty()) {
            Integer index = addSubIndices.get(RANDOM.nextInt(addSubIndices.size()));
            for (int i = 0; i < tokens.length; i++) {
                if (i == index - 1) {
                    result.append("(");
                }
                result.append(tokens[i]);
                if (i == index + 1) {
                    result.append(")");
                } else if (i < tokens.length - 1) {
                    result.append(" ");
                }
            }
        } else {
            result.append(expression);
        }
        return result.toString();
    }

    public static void checkExercisesAnswers(String exercisesFileName, String answerFileName) {
        if (StringUtils.isBlank(exercisesFileName) && StringUtils.isBlank(answerFileName)) {
            return;
        }
        String exercisesFilePath = System.getProperty("user.dir") + "/" + exercisesFileName;
        String answerFilePath = System.getProperty("user.dir") + "/" + answerFileName;
        FileUtils.validateFileExists(exercisesFilePath);
        FileUtils.validateFileExists(answerFilePath);
        List<String> exercises = FileUtil.readUtf8Lines(exercisesFilePath);
        List<String> answers = FileUtil.readUtf8Lines(answerFilePath);

        if (exercises.size() != answers.size()) {
            throw new IllegalStateException("题目和答案的数量不一致！");
        }

        System.out.println("开始校验题目和答案...");
        List<String> rightAnswers = new ArrayList<>();
        List<String> wrongAnswers = new ArrayList<>();

        for (int i = 0; i < exercises.size(); i++) {
            String[] parts = exercises.get(i).trim().split("\\.\\s+");
            String exercise = parts[1];
            String answer = answers.get(i).trim().split("\\.\\s+")[1];
            String infixToPostfix = ExpressionUtils.infixToPostfix(exercise);
            Fraction result = ExpressionUtils.evaluatePostfixAllowNegative(infixToPostfix);

            if (Objects.equals(result.toString(), answer)) {
                rightAnswers.add(String.valueOf(parts[0]));
            } else {
                wrongAnswers.add(String.valueOf(parts[0]));
            }
        }

        List<String> gradeList = new ArrayList<>();
        gradeList.add("Correct: " + rightAnswers.size() + "(" + String.join(", ", rightAnswers) + ")");
        gradeList.add("Wrong: " + wrongAnswers.size() + "(" + String.join(", ", wrongAnswers) + ")");
        FileUtil.writeUtf8Lines(gradeList, System.getProperty("user.dir") + "/Grade.txt");

        System.out.println("校验完成，结果已保存至 " + System.getProperty("user.dir") + "/Grade.txt");

    }
}
