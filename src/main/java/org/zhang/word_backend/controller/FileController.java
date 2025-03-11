package org.zhang.word_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${spring.image-upload.path}")
    private String BASE_IMAGE_PATH;

    @GetMapping("/image/{filename}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String filename){
        try {
            // 创建文件路径
            Path path = Paths.get(BASE_IMAGE_PATH + "/"+filename);
            // 创建资源对象
            UrlResource resource = new UrlResource(path.toUri());
            // 根据文件扩展名动态设置媒体类型
            String contentType = Files.probeContentType(path);
            // 创建媒体类型对象
            MediaType mediaType = MediaType.parseMediaType(contentType);
            log.info("文件类型：{}",contentType);
            log.info("文件路径：{}",path);
            log.warn("文件是否存在：{}",resource.exists());
            if (resource.exists()){
                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .body(resource);
            }else {
                log.error("文件不存在");
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("文件为空");
            }

            Path path = Paths.get(BASE_IMAGE_PATH + "/" + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path);
            log.info("文件上传成功: {}", path);
            return ResponseEntity.ok("文件上传成功");
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }
}
