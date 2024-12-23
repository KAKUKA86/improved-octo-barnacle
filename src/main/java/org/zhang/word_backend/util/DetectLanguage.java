package org.zhang.word_backend.util;

import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;

public class DetectLanguage {
    public static String simpyDetectLanguage(String text) {
        DetectLanguage detectLanguage = new DetectLanguage();
        if (detectLanguage.containsChinese(text))
            return "zh";
        else if (detectLanguage.containsJapanese(text))
            return "ja";
        else return "Unknown";
    }

    /**
     * 语言识别器
     *
     * @param text 输入的字符串
     * @return 语言类别
     */
    private String detectLanguage(String text) {
        try {
            LanguageDetector detector = LanguageDetector.getDefaultLanguageDetector();
            detector.loadModels();
            LanguageResult result = detector.detect(text);
            return result.getLanguage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    private boolean containsJapanese(String input) {
        // 平假名（ひらがな）正则
        String hiraganaPattern = "[\\u3040-\\u309F]+";
        // 片假名（カタカナ）正则
        String katakanaPattern = "[\\u30A0-\\u30FF]+";

        return input.matches(".*(" + hiraganaPattern + "|" + katakanaPattern + ").*");
    }

    // 判断是否为中文（简体或繁体）
    private boolean containsChinese(String input) {
        // 中文汉字正则（包括简体和繁体）
        String chinesePattern = "[\\u4e00-\\u9fa5]+";
        return input.matches(".*" + chinesePattern + ".*");
    }
}
