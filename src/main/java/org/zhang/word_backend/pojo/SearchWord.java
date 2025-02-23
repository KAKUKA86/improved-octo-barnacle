package org.zhang.word_backend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
/*
    className: searchWord
    description: 搜索词类
 */
public class SearchWord {
    //总记录数
    private Long total;
    //总页数
    private Long pages;
    //当前页
    private Long current;
    //每页大小
    private Long size;
    //查询的字符串
    private String searchWord;
    //当前页的数据
    private List<Word> wordListRecords;
}
