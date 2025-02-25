package org.zhang.word_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.zhang.word_backend.pojo.Glossary;

@Mapper
public interface UserCreateMapper extends BaseMapper<Glossary> {
    int insertGlossary(Glossary glossary);
}
