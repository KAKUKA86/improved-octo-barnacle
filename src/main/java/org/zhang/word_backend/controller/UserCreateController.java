package org.zhang.word_backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.service.UserCreateService;
import org.zhang.word_backend.util.result.GlossaryResultSet;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/new")
public class UserCreateController {
    @Value("${spring.image-upload.path}")
    private String BASE_IMAGE_PATH;

    @Resource
    private UserCreateService service;

}
