package org.zhang.word_backend.service.impl;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.UserSearchMapper;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.pojo.Word;
import org.zhang.word_backend.util.DetectLanguage;
import org.zhang.word_backend.util.result.WordListResult;
import org.zhang.word_backend.util.result.WordResult;
import org.zhang.word_backend.service.UserSearchService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Resource
    UserSearchMapper mapper;

    /**
     * 模糊查询
     * @param searchWord 输入的字符串或字符
     * @return 查询列表
     */
    @Override
    public WordListResult fuzzyQueries(SearchWord searchWord) {
        System.out.println("进入service.fuzzyQueries");
        String d = DetectLanguage.detectLanguage(searchWord.getOption());
        List<Word> wordList = mapper.fuzzyQueries(searchWord.getOption());
        return null;
    }

    @Override
    public WordResult getWordInfo(SearchWord searchWord) {
        System.out.println("进入service");
        Word words = mapper.selectWordInfo(searchWord.getOption());
        System.out.println(words.toString());
        if (words.toString() == null) return new WordResult(204, "无内容");
        return new WordResult(200, "ok", words);
    }


}
