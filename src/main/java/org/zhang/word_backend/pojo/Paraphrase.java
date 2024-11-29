package org.zhang.word_backend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Paraphrase {
    String wordId;
    String paraId;
    String paraContent;
    String paraType;
    List<IllustrativeSentence> illustrativeSentences;
}
