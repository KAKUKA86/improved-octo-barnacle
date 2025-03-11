package org.zhang.word_backend.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.pojo.PagingSearchGlossary;
import org.zhang.word_backend.service.GlossaryService;
import org.zhang.word_backend.util.result.GlossaryResultSet;


import javax.annotation.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/glossaries")
public class GlossaryController {
    private static final Logger logger = LoggerFactory.getLogger(GlossaryController.class);
    @Resource
    private GlossaryService glossaryService;

    @Value("${spring.image-upload.path}")
    private String BASE_IMAGE_PATH;

    //不需要关键字搜索
    @PostMapping("/recommend")
    public ResponseEntity<GlossaryResultSet> getRecommendGlossary(@RequestBody PagingSearchGlossary pagingSearchGlossary) {
        logger.info("获取推荐词单");
        List<Glossary> glossaries = glossaryService.getRecommendGlossary(pagingSearchGlossary);
        glossaries.forEach(glossary -> {
            Path path = Paths.get(BASE_IMAGE_PATH, glossary.getCoverImageName());
            try {
                UrlResource resource = new UrlResource(path.toUri());
                if (resource.exists()) {
                    glossary.setCoverUrl(resource.getURL().toString());
                }
            } catch (Exception e) {
                logger.error("获取封面图片失败", e);
            }
        });
        GlossaryResultSet resultSet = new GlossaryResultSet(true,glossaries);
        if (glossaries.isEmpty())
            resultSet.setMessage("没有找到相关词单");
        return new ResponseEntity<>(resultSet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GlossaryResultSet> addGlossary(@RequestBody Glossary glossary) {
        logger.info("添加词单");
        GlossaryResultSet resultSet = new GlossaryResultSet();
        try {
            logger.info("词单属性:{}",glossary);
            glossaryService.addGlossary(glossary);
            resultSet.setMessage("添加成功");
            return new ResponseEntity<>(resultSet, HttpStatus.OK);
        } catch (Exception e){
            logger.error("添加词单失败", e);
            resultSet.setMessage("添加失败");
            return new ResponseEntity<>(resultSet, HttpStatus.BAD_REQUEST);
        }
    }
}
