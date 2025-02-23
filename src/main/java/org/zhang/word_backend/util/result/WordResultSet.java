package org.zhang.word_backend.util.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zhang.word_backend.pojo.Word;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordResultSet {
    Integer status;
    String message;
    Object word;

    public WordResultSet(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
