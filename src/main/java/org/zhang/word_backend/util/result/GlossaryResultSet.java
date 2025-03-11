package org.zhang.word_backend.util.result;

import lombok.*;
import org.zhang.word_backend.pojo.Glossary;

import javax.annotation.Resource;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlossaryResultSet {
    private Integer total;
    private Integer pages;
    private Integer current;
    private Integer size;

    private Integer status;
    private String message;
    private List<Glossary> glossaries;
    private Glossary glossary;

    public GlossaryResultSet(int i, String message, List<Glossary> records) {
        this.status = i;
        this.message = message;
        this.glossaries = records;
    }

    public GlossaryResultSet(boolean b, String message) {
        this.status = b ? 200 : 400;
        this.message = message;
    }

    public GlossaryResultSet(boolean b,List<Glossary> glossaries) {
        this.status = b ? 200 : 400;
        this.glossaries = glossaries;
    }
}
