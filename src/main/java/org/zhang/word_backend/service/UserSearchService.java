package org.zhang.word_backend.service;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.util.result.WordListResult;
import org.zhang.word_backend.util.result.WordResult;

@Service
public interface UserSearchService {
    WordListResult fuzzyQueries(SearchWord searchWord);
    WordResult getWordInfo(SearchWord searchWord);
}
