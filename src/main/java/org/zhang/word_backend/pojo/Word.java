package org.zhang.word_backend.pojo;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter

public class Word {
    String wordId;
    String word;
    Integer dId;
    String hiragana;
    String romaji;
    String pronunciation;
    List<Paraphrase> paraphrases;
}
