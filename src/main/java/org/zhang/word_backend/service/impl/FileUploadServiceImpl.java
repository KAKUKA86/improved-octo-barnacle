package org.zhang.word_backend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhang.word_backend.service.FileUploadService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "." + file.getOriginalFilename();
        Files.createDirectories(rootLocation);
        Files.copy(file.getInputStream(), rootLocation.resolve(filename));
        return "/files/" + filename;
    }
}

