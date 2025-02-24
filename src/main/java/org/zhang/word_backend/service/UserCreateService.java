package org.zhang.word_backend.service;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.util.result.GlossaryResultSet;

@Service
public interface UserCreateService {

    GlossaryResultSet newGlossary(Glossary glossary);
}
