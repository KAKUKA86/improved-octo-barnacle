package org.zhang.word_backend.util.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.pojo.Word;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//
public class WordListResultSet<T> {
    //状态码
    private Integer status;
    //信息
    private String message;
    private SearchWord searchWord;
    private List<SearchWord> data;
}
