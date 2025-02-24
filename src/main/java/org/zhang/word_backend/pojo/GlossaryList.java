package org.zhang.word_backend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class GlossaryList {
    private int wordId;
    private Timestamp createAt;
}
