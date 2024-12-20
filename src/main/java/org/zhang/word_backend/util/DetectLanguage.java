package org.zhang.word_backend.util;

import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;

public class DetectLanguage {
    /**
     * 语言识别器
     * @param text 输入的字符串
     * @return 语言类别
     */
    public static String detectLanguage(String text) {
        try {
            LanguageDetector detector = LanguageDetector.getDefaultLanguageDetector();
            detector.loadModels();
            LanguageResult result = detector.detect(text);
            return result.getLanguage();
        }catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
}
