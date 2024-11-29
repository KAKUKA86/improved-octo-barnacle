package org.zhang.word_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhang.word_backend.pojo.Word;
import org.zhang.word_backend.result.WordResult;
import org.zhang.word_backend.service.UserSearchService;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class UserSearchController {
    @Resource
    UserSearchService userSearchService;
    @RequestMapping("/search")
    public WordResult Search(@RequestBody Word word) {
        return userSearchService.getWordList(word);
    }
}
