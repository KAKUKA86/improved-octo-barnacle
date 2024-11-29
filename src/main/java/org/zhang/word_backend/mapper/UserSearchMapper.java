package org.zhang.word_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zhang.word_backend.pojo.Word;

import java.util.List;

@Mapper
public interface UserSearchMapper {
    Word selectWordInfo(String word);
}
