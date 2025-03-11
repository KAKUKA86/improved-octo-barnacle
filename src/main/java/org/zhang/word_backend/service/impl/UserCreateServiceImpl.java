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
    private UserCreateMapper mapper;

    @Override
    public ResponseEntity<GlossaryResultSet> newGlossary(Glossary glossary) {
        int affectedRows = mapper.insertGlossary(glossary);
        if (affectedRows != 1)
            return ResponseEntity.badRequest().body(new GlossaryResultSet(false, "新建词单失败"));
        return ResponseEntity.ok(new GlossaryResultSet(true, "新建词单成功"));
    }
}
