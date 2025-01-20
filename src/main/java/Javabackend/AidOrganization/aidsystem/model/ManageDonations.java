package com.example.aidsystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class ManageDonations {
    @Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String donorName;
    private Double amount;

    // Getters and setters
}

}
