package org.zhang.word_backend.pojo;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
/*
    className: word
    description: 单词类
 */
public class Word {
    private String wordId;
    private String word;
    private Integer dId;
    private String hiragana;
    private String romaji;
    private String pronunciation;
    private String dictionaryNames;
    private List<Paraphrase> paraphrases;
}
