package org.zhang.word_backend.service;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.pojo.PagingSearchGlossary;

import java.util.List;

@Service
public interface GlossaryService {

    List<Glossary> getRecommendGlossary(PagingSearchGlossary pagingSearchGlossary);

    void addGlossary(Glossary glossary);
}
