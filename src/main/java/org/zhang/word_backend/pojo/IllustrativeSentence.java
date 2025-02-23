package org.zhang.word_backend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
/*
  className: illustrativeSentence
  description: 例句类
 */
public class IllustrativeSentence {
    String paraId;
    String senContent;
    String senTrans;
}
