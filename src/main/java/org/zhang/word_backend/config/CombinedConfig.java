package org.zhang.word_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@RestController
@RequestMapping("/files")
public class CombinedConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(CombinedConfig.class);

    // 获取桌面路径
    private String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "uploads";

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("文件名称：" + file.getOriginalFilename());
        if (file.isEmpty()) {
            return new ResponseEntity<>("文件为空", HttpStatus.BAD_REQUEST);
        }

        try {
            // 创建保存文件的目录（如果不存在）
            Path directoryPath = Paths.get(desktopPath);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // 构建文件保存路径
            Path targetFilePath = directoryPath.resolve(file.getOriginalFilename());

            // 保存文件
            file.transferTo(targetFilePath.toFile());
            System.out.println("文件保存路径: " + targetFilePath.toString());

            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("文件上传失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 确保路径格式正确
        Path absolutePath = Paths.get(desktopPath).toAbsolutePath();

        // 检查路径是否存在并可读
        if (Files.exists(absolutePath) && Files.isDirectory(absolutePath)) {
            logger.info("静态资源文件夹: {}", absolutePath.toString());
            registry.addResourceHandler("/files/**")
                    .addResourceLocations("file:" + absolutePath + "/");
        } else {
            logger.error("静态资源文件夹不存在: {}", absolutePath.toString());
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/files/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
