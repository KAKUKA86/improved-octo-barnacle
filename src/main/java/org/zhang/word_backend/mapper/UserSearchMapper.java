package org.zhang.word_backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zhang.word_backend.pojo.SearchWord;
import org.zhang.word_backend.pojo.Word;


@Mapper
public interface UserSearchMapper {

    Word selectWordInfo(String word);

    Page<SearchWord> selectWordInfoPage(Page<SearchWord> page, @Param("word") String word);

    Integer selectJaKanjiWord(String searchWord);
}
