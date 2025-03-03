package com.example.demo.service;

import com.example.demo.model.Pharmacy;
import com.example.demo.repository.PharmacyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public void loadPharmaciesFromJson() {
        try {
            // Define the path to the JSON file
            File jsonFile = new File(getClass().getClassLoader().getResource("pharmacies.json").getFile());

            // Use Jackson ObjectMapper to read and parse the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pharmacy> pharmacies = objectMapper.readValue(jsonFile, new TypeReference<List<Pharmacy>>() {});

            // Save the list of pharmacies to the database
            pharmacyRepository.saveAll(pharmacies);

            System.out.println("Data loaded successfully into the database!");

        } catch (IOException e) {
            throw new RuntimeException("Error loading pharmacies JSON", e);
        }
    }
    public List<Pharmacy> getPharmaciesByCityAndStatus(String street, String status) {
        // Log to debug
        System.out.println("Searching for pharmacies with street: " + street + " and status: " + status);

        // Handle default values
        if (street == null || street.isEmpty()) {
            street = "Targa";  // Set a default value for street if it's empty or null
        }
        if (status == null || status.isEmpty()) {
            status = "Jour";  // Set a default value for status if it's empty or null
        }

        // Call the repository method to fetch pharmacies
        return pharmacyRepository.findByStreetIgnoreCaseAndStatusIgnoreCase(street, status);
    }

    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }
}
