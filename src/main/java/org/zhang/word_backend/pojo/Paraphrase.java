package org.zhang.word_backend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
/*
    className: paraphrase
    description: 释义类
 */
public class Paraphrase {
    private String paraContentZh;
    private String paraContentJa;
    private String paraType;
    private List<IllustrativeSentence> illustrativeSentences;
}
