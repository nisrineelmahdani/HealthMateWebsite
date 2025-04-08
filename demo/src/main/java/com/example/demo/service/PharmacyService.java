package com.example.demo.service;

import com.example.demo.model.Pharmacy;
import com.example.demo.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Add @Service annotation to mark this as a service class
public class PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    // Method to get all pharmacies
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();  // Fetch all pharmacies from the database
    }

    // Method to get pharmacies filtered by street and status
    public List<Pharmacy> getPharmaciesByCityAndStatus(String street, String status) {
        return pharmacyRepository.findByStreetAndStatus(street, status);  // Call repository method to filter pharmacies
    }
}
