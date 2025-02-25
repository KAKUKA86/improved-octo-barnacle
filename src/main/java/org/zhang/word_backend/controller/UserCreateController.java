package org.zhang.word_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhang.word_backend.pojo.Glossary;
import org.zhang.word_backend.service.UserCreateService;
import org.zhang.word_backend.util.result.GlossaryResultSet;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/new")
public class UserCreateController {
    @Resource
    UserCreateService userCreateService;
    /**
     * 新建词单
     * @param glossary 词单
     * @return 是否创建成功
     */
//    @PostMapping("/addGlossary")
//    public GlossaryResultSet newGlossary(@RequestBody Glossary glossary){
//        //TODO
//        return userCreateService.newGlossary(glossary);
//    }
    @PostMapping("/addGlossary")
    public ResponseEntity<?> newGlossary(@ModelAttribute Glossary glossary, @RequestParam("image") MultipartFile imageFile) {
        return userCreateService.newGlossary(glossary, imageFile);
    }

}
