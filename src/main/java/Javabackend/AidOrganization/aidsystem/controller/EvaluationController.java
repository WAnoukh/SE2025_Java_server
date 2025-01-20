package com.example.aidsystem.controller;

import com.example.aidsystem.model.VolunteerEvaluation;
import com.example.aidsystem.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/add")
    public ResponseEntity<?> addEvaluation(@RequestBody VolunteerEvaluation evaluation) {
        evaluationService.addEvaluation(evaluation);
        return ResponseEntity.ok("Evaluation added successfully.");
    }
}
