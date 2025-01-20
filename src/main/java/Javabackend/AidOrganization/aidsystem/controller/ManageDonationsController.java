package com.example.aidsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aidsystem.model.Donation;
import com.example.aidsystem.service.DonationService;

@RestController
@RequestMapping("/donations")
public class ManageDonationsController {

    @Autowired
    private DonationService donationService;

    @PostMapping("/add")
    public ResponseEntity<?> addDonation(@RequestBody Donation donation) {
        donationService.addDonation(donation);
        return ResponseEntity.ok("Donation added successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Donation>> getDonations() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }
}
