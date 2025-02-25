package org.zhang.word_backend.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhang.word_backend.mapper.UserCreateMapper;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.service.FileUploadService;
import org.zhang.word_backend.service.UserCreateService;
import org.zhang.word_backend.util.result.GlossaryResultSet;

import javax.annotation.Resource;

@Service
public class UserCreateServiceImpl implements UserCreateService {
    @Resource
    private UserCreateMapper userCreateMapper;

    @Resource
    private FileUploadService fileUploadService;

    @Override
    public ResponseEntity<GlossaryResultSet> newGlossary(Glossary glossary, MultipartFile imageFile) {
        try{
            String imagePath = fileUploadService.uploadFile(imageFile);
            glossary.setCoverUrl(imagePath);
            int affectedRows = userCreateMapper.insertGlossary(glossary);
            if (affectedRows > 0)
                return ResponseEntity.ok(new GlossaryResultSet(200, "创建词单成功", null, glossary));
            else
                return ResponseEntity.status(500).body(new GlossaryResultSet(500, "创建词单失败", null, null));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new GlossaryResultSet(500, "创建词单失败", null, null));
        }
    }
}
