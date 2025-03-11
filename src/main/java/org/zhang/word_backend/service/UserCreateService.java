package org.zhang.word_backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.util.result.GlossaryResultSet;

@Service
public interface UserCreateService {
    ResponseEntity<GlossaryResultSet> newGlossary(Glossary glossary);
}
