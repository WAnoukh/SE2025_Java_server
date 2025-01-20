package com.example.aidsystem.controller;

import com.example.aidsystem.model.Donation;
import com.example.aidsystem.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping("/create")
    public String createDonation(@RequestBody Donation donation) {
        donationService.saveDonation(donation);
        return "Donation created successfully";
    }

    @GetMapping("/all")
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }
}
