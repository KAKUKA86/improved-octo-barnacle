package org.zhang.word_backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.UserSearchMapper;
import org.zhang.word_backend.pojo.*;
import org.zhang.word_backend.util.DetectLanguage;
import org.zhang.word_backend.util.result.GlossaryResultSet;
import org.zhang.word_backend.util.result.WordListResultSet;
import org.zhang.word_backend.util.result.WordResultSet;
import org.zhang.word_backend.service.UserSearchService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Resource
    UserSearchMapper mapper;

    /**
     * 模糊分页查询
     *
     * @param searchWord 查询的单词（字符串）
     * @param current    分页页数
     * @param size       分页长度
     * @return 暂时没有定
     */
    @Override
    public WordListResultSet<SearchWord> getWordPage(String searchWord, Long current, Long size) {
        Page<SearchWord> page = new Page<>(current, size);
        WordListResultSet<SearchWord> wordListResultSet = new WordListResultSet<>();
        String lang = DetectLanguage.simpyDetectLanguage(searchWord);
        System.out.println(searchWord);
        if (lang.equals("ja")) {
            System.out.println("进入日语查询");
            Page<SearchWord> resultPage = mapper.selectWordInfoPage(page, searchWord);
            System.out.println(resultPage.getTotal());
            if (resultPage.getTotal() == 0) {
                wordListResultSet.setStatus(204);
                wordListResultSet.setMessage("没有相似数据");
                return wordListResultSet;
            }
            wordListResultSet.setStatus(200);
            wordListResultSet.setMessage("ok");
            wordListResultSet.setList(resultPage.getRecords());
        }else{
            System.out.println("进入中文查询");
            if(mapper.selectJaKanjiWord(searchWord) != 0){
                Page<SearchWord> resultPage = mapper.selectWordInfoPage(page, searchWord);
                if (resultPage.getTotal() == 0) {
                    wordListResultSet.setStatus(204);
                    wordListResultSet.setMessage("没有相似数据");
                }else{
                    wordListResultSet.setStatus(200);
                    wordListResultSet.setMessage("ok");
                    System.out.println(Arrays.toString(resultPage.getRecords().toArray()));
                    wordListResultSet.setList(resultPage.getRecords());
                }
            }else{
                wordListResultSet.setStatus(400);
                wordListResultSet.setMessage("输入非法，或查询失败");
            }
        }
        return wordListResultSet;
    }

    @Override
    public WordResultSet getWordPageInfo(String word_id) {
        System.out.println("进入service"+word_id);
        List<Word> words = mapper.selectWordInfo(word_id);

        if (words.isEmpty()) return new WordResultSet(204, "无内容");
        return new WordResultSet(200, "ok",words);
    }

    /**
     * 分页查询词单
     * @param searchGlossary 查询词单
     * @return 词单
     */
    @Override
    public GlossaryResultSet getAllGlossary(SearchGlossary searchGlossary) {
        //TODO 词单分页查询
        Page<Glossary> page = new Page<>(searchGlossary.getCurrent(), searchGlossary.getSize());
        Page<Glossary> glossaryPage = mapper.selectGlossaryPage(page, searchGlossary);
        if (glossaryPage.getTotal() == 0) {
            return new GlossaryResultSet(204, "无内容", glossaryPage.getRecords());
        }
        if (glossaryPage.getRecords().isEmpty()) {
            return new GlossaryResultSet(204, "无内容", glossaryPage.getRecords());
        }
        return new GlossaryResultSet(200, "ok", glossaryPage.getRecords());
    }
}
