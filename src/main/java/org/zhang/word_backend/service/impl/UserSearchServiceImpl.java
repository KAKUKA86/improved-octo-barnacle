package org.zhang.word_backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.UserSearchMapper;
import org.zhang.word_backend.pojo.*;
import org.zhang.word_backend.util.DetectLanguage;
import org.zhang.word_backend.util.result.GlossaryResultSet;
import org.zhang.word_backend.util.result.WordListResultSet;
import org.zhang.word_backend.util.result.WordResultSet;
import org.zhang.word_backend.service.UserSearchService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Resource
    UserSearchMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(UserSearchServiceImpl.class);
    private static final int STATUS_OK = 200;
    private static final int STATUS_NO_CONTENT = 204;
    private static final int STATUS_BAD_REQUEST = 400;
    private static final String MESSAGE_OK = "ok";
    private static final String MESSAGE_NO_SIMILAR_DATA = "没有相似数据";
    private static final String MESSAGE_INVALID_INPUT_OR_QUERY_FAILED = "输入非法，或查询失败";
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

        if (current == null || size == null || current <= 0 || size <= 0) {
            return createErrorResult(STATUS_BAD_REQUEST, "分页参数无效");
        }

        Page<SearchWord> page = new Page<>(current, size);
        WordListResultSet<SearchWord> wordListResultSet = new WordListResultSet<>();
        try {
            String lang = DetectLanguage.simpyDetectLanguage(searchWord);
            logger.info("Detected language: {}", lang);

            if ("ja".equals(lang)) {
                logger.info("进入日语查询");
                return handleQuery(page, searchWord, wordListResultSet);

            } else {
                logger.info("进入中文查询");
                if (mapper.selectJaKanjiWord(searchWord) != 0) {
                    return handleQuery(page, searchWord, wordListResultSet);
                } else {
                    return createErrorResult(STATUS_BAD_REQUEST, MESSAGE_INVALID_INPUT_OR_QUERY_FAILED);
                }

            }
        } catch (Exception e) {
            logger.error("查询过程中发生异常", e);
            return createErrorResult(STATUS_BAD_REQUEST, "查询过程中发生异常");
        }
    }

    private WordListResultSet<SearchWord> handleQuery(Page<SearchWord> page, String searchWord, WordListResultSet<SearchWord> wordListResultSet) {
        try {
            Page<SearchWord> resultPage = mapper.selectWordInfoPage(page, searchWord);
            logger.info("查询结果总数: {}", resultPage.getTotal());

            if (resultPage.getTotal() == 0) {
                return createErrorResult(STATUS_NO_CONTENT, MESSAGE_NO_SIMILAR_DATA);
            }

            wordListResultSet.setStatus(STATUS_OK);
            wordListResultSet.setMessage(MESSAGE_OK);
            wordListResultSet.setList(resultPage.getRecords());
            return wordListResultSet;
        } catch (Exception e) {
            logger.error("查询过程中发生异常", e);
            return createErrorResult(STATUS_BAD_REQUEST, "查询过程中发生异常");
        }
    }

    private WordListResultSet<SearchWord> createErrorResult(int status, String message) {
        WordListResultSet<SearchWord> errorResult = new WordListResultSet<>();
        errorResult.setStatus(status);
        errorResult.setMessage(message);
        return errorResult;
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
     * @param pagingSearchGlossary 查询词单
     * @return 词单
     */
    @Override
    public GlossaryResultSet getAllGlossary(PagingSearchGlossary pagingSearchGlossary) {
        Page<Glossary> page = new Page<>(pagingSearchGlossary.getCurrent(), pagingSearchGlossary.getSize());
        Page<Glossary> glossaryPage = mapper.selectGlossaryPage(page, pagingSearchGlossary);
        if (glossaryPage.getTotal() == 0) {
            return new GlossaryResultSet(204, "无内容",glossaryPage.getRecords());
        }
        if (glossaryPage.getRecords().isEmpty()) {
            return new GlossaryResultSet(204, "无内容", glossaryPage.getRecords());
        }
        return new GlossaryResultSet(200, "ok", glossaryPage.getRecords());
    }
}
