package com.example.aidsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aidsystem.model.AidActivity;
import com.example.aidsystem.service.AidActivityService;  // Импорт сервиса

@RestController
@RequestMapping("/activities")
public class AidActivityController {

    @Autowired
    private AidActivityService aidActivityService;  // Инъекция сервиса

    @PostMapping("/add")
    public ResponseEntity<?> addActivity(@RequestBody AidActivity activity) {
        aidActivityService.addActivity(activity);  // Добавление активности
        return ResponseEntity.ok("Activity added successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<AidActivity>> getActivities() {
        return ResponseEntity.ok(aidActivityService.getAllActivities());  // Получение списка активностей
    }
}
