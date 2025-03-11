package org.zhang.word_backend.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Zhang
 * &#064;date  2025/2/24 17:09
 * &#064;description:  仅作基础分页，不做模糊查询
 */
@Data
public class PagingSearchGlossary {
    private int total;
    private int pages;
    private int current;
    private int size;
    
    //可能存在的搜索词
    private String searchGlossary;
    //可能存在的词单类别
    private int categoryId;
    //返回的当前页数据
    private List<Glossary> list;
}
