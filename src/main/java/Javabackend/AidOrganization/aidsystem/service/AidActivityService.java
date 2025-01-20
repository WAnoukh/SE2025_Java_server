package com.example.aidsystem.service;

import com.example.aidsystem.model.AidActivity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AidActivityService {

    private List<AidActivity> activities = new ArrayList<>();  // Пример хранилища для активностей

    public void addActivity(AidActivity activity) {
        activities.add(activity);  // Логика добавления активности
    }

    public List<AidActivity> getAllActivities() {
        return activities;  // Логика получения всех активностей
    }
}
