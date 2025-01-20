package com.example.aidsystem.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    private final Path rootLocation = Paths.get("uploaded-documents");

    // Метод для загрузки документа
    public ResponseEntity<Resource> downloadDocument(Long id) {
        try {
            // Здесь вы будете искать файл по ID, например, через базу данных
            String fileName = "document_" + id + ".pdf";  // Примерный файл, это можно заменить на реальную логику
            Path filePath = rootLocation.resolve(fileName);

            if (Files.exists(filePath)) {
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists()) {
                    return ResponseEntity.ok()
                            .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                            .body(resource);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
