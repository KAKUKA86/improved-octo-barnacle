package org.zhang.word_backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.GlossaryMapper;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.pojo.PagingSearchGlossary;
import org.zhang.word_backend.service.GlossaryService;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GlossaryServiceImpl implements GlossaryService {

    @Resource
    GlossaryMapper mapper;
    @Override
    public List<Glossary> getRecommendGlossary(PagingSearchGlossary pagingSearchGlossary) {
        Page<Glossary> page = new Page<>(pagingSearchGlossary.getCurrent(), pagingSearchGlossary.getSize());
        Page<Glossary> glossaryPage = mapper.selectGlossaryPage(page);
        if (glossaryPage.getTotal() == 0)
            throw new RuntimeException("无内容");
        return glossaryPage.getRecords();
    }

    @Override
    public void addGlossary(Glossary glossary) {
        mapper.insertGlossary(glossary);
    }

}
