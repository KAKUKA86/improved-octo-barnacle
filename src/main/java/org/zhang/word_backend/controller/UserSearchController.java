package org.zhang.word_backend.controller;


import org.springframework.web.bind.annotation.*;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.pojo.SearchGlossary;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.pojo.Word;
import org.zhang.word_backend.util.result.GlossaryResultSet;
import org.zhang.word_backend.util.result.WordListResultSet;
import org.zhang.word_backend.util.result.WordResultSet;
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
     *
     * @param searchWord 需要查询的字符串
     * @return 单词列表
     */
    @PostMapping("/fuzzy")
    public WordListResultSet<SearchWord> fuzzy(@RequestBody SearchWord searchWord) {
        System.out.println(searchWord);
        return userSearchService.getWordPage(searchWord.getSearchWord(), searchWord.getCurrent(),searchWord.getSize());
    }

    /**
     * 查询单词
     *
     * @param word_id 查询单词id
     * @return 单词信息
     */
    @GetMapping("/byWord")
    public WordResultSet searchById(@RequestParam String word_id) {
        return userSearchService.getWordPageInfo(word_id);
    }

    /**
     * 分页查询词单（包含关键字搜索）
     * @param searchGlossary 显示所有词单
     * @return 分页显示所有词单
     */
    @PostMapping("/glossary")
    public GlossaryResultSet getAllGlossary (@RequestBody SearchGlossary searchGlossary) {
        return userSearchService.getAllGlossary(searchGlossary);
    }

}
