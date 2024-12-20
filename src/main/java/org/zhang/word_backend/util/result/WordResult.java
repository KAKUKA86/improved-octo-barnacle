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
public class WordResult {
    Integer status;
    String message;
    Word word;

    public WordResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
