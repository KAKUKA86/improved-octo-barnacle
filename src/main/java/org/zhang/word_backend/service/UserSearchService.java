package org.zhang.word_backend.service;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.pojo.Word;
import org.zhang.word_backend.result.WordResult;

@Service
public interface UserSearchService {
    WordResult getWordList(Word word);
}
