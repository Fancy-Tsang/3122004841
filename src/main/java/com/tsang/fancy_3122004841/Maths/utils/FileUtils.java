package com.tsang.fancy_3122004841.Maths.utils;

import java.io.File;

public class FileUtils {
    private static final String TXT_FILE_PATTERN = "^[a-zA-Z0-9_-]+\\.txt$";

    public static boolean isValidTxtFileName(String fileName) {
        return fileName.matches(TXT_FILE_PATTERN);
    }

    public static boolean isNotValidTxtName(String fileName) {
        return !isValidTxtFileName(fileName);
    }

    public static void deleteFileIfExists(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.delete()) {
            System.out.println("旧题目文件已删除: " + filePath);
        }
    }

    public static void validateFileExists(String filePath) {
        if (!new File(filePath).exists()) {
            throw new IllegalArgumentException("文件不存在: " + filePath);
        }
    }
}
