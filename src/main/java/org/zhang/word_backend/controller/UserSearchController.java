package org.zhang.word_backend.controller;

import org.springframework.web.bind.annotation.*;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.util.result.WordListResult;
import org.zhang.word_backend.util.result.WordResult;
import org.zhang.word_backend.service.UserSearchService;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class UserSearchController {
    @Resource
    UserSearchService userSearchService;

    /**
     * 模糊查询
     * @param searchWord 需要查询的字符串
     * @return 单词列表
     */
    @PostMapping("/fuzzy")
    public WordListResult fuzzy(@RequestBody SearchWord searchWord) {
        return userSearchService.fuzzyQueries(searchWord);
    }
    /**
     * 查询单词
     * @param searchWord 查询单词
     * @return 单词信息
     */
    @PostMapping("/byWord")
    public WordResult search(@RequestBody SearchWord searchWord) {
        return userSearchService.getWordInfo(searchWord);
    }
}
