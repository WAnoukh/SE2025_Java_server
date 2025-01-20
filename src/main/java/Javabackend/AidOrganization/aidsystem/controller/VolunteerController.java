package com.example.aidsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aidsystem.model.Volunteer;
import com.example.aidsystem.service.VolunteerService;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/add")
    public ResponseEntity<?> addVolunteer(@RequestBody Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        return ResponseEntity.ok("Volunteer added successfully.");
    }

    @GetMapping("/assign")
    public ResponseEntity<?> assignVolunteer(@RequestParam Long volunteerId, @RequestParam Long activityId) {
        volunteerService.assignVolunteerToActivity(volunteerId, activityId);
        return ResponseEntity.ok("Volunteer assigned to activity.");
    }
}
