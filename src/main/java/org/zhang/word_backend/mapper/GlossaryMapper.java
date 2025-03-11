package org.zhang.word_backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.zhang.word_backend.pojo.Glossary;

@Mapper
public interface GlossaryMapper {
    Page<Glossary> selectGlossaryPage(Page<Glossary> page);

    void insertGlossary(Glossary glossary);
}
