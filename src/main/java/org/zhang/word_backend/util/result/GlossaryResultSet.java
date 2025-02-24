package org.zhang.word_backend.util.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.pojo.GlossaryList;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlossaryResultSet {
    private Integer status;
    private String message;
    private List<Glossary> glossaries;
    private Glossary glossary;
}
