package org.zhang.word_backend.service.impl;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.UserSearchMapper;
import org.zhang.word_backend.pojo.Word;
import org.zhang.word_backend.result.WordResult;
import org.zhang.word_backend.service.UserSearchService;

import javax.annotation.Resource;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Resource
    UserSearchMapper mapper;
    @Override
    public WordResult getWordList(Word word) {
        System.out.println("进入service");
        Word words = mapper.selectWordInfo(word.getWord());
        System.out.println(words.toString());
        return null;
    }
}
