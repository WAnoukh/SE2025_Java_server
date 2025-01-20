package com.example.aidsystem.service;

import com.example.aidsystem.model.Donation;
import com.example.aidsystem.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public void addDonation(Donation donation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addDonation'");
    }
}
