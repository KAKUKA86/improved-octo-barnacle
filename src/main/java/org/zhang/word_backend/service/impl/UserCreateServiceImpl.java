package org.zhang.word_backend.service.impl;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.mapper.UserCreateMapper;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.service.UserCreateService;
import org.zhang.word_backend.util.result.GlossaryResultSet;

import javax.annotation.Resource;

@Service
public class UserCreateServiceImpl implements UserCreateService {
    @Resource
    private UserCreateMapper userCreateMapper;

    @Override
    public GlossaryResultSet newGlossary(Glossary glossary) {
        //处理从前端传来的图片
    return null;
    }
}
