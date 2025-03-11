package org.zhang.word_backend.service;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.pojo.PagingSearchGlossary;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.util.result.GlossaryResultSet;
import org.zhang.word_backend.util.result.WordListResultSet;
import org.zhang.word_backend.util.result.WordResultSet;

@Service
public interface UserSearchService {
    WordListResultSet<SearchWord> getWordPage(String searchWord, Long current, Long size);

    WordResultSet getWordPageInfo(String word_id);
    //分页查询词单
    GlossaryResultSet getAllGlossary(PagingSearchGlossary pagingSearchGlossary);
}
