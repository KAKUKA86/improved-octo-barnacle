package org.zhang.word_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.zhang.word_backend.mapper")
public class WordBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordBackendApplication.class, args);
    }

}
