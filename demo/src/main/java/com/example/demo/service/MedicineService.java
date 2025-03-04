package com.example.demo.service;

import com.example.demo.model.Medicine;
import com.example.demo.model.Pharmacy;
import com.example.demo.repository.MedicineRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable; // Correct Pageable import
import org.springframework.data.domain.PageRequest; // Correct PageRequest import


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class MedicineService {



    private final MedicineRepository medicineRepository;

    @Autowired

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

   /* public void loadMedicinesFromJson() {
        try {
            // Charger le fichier JSON depuis le dossier resources/
            ClassPathResource resource = new ClassPathResource("fixedMedicine.json");
            InputStream inputStream = resource.getInputStream();

            // Convertir JSON en liste de Medicine
            ObjectMapper objectMapper = new ObjectMapper();
            List<Medicine> medicines = objectMapper.readValue(inputStream, new TypeReference<List<Medicine>>() {});

            // Vérifier les données avant insertion
            if (medicines.isEmpty()) {
                System.err.println("❌ Aucune donnée trouvée dans le fichier JSON !");
                return;
            }

            // Sauvegarder en base de données
            medicineRepository.saveAll(medicines);
            System.out.println("✅ Medicines loaded successfully into the database!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Erreur lors du chargement du JSON", e);
        }
    }


    @PostConstruct
    public void init() {
        loadMedicinesFromJson();
    }*/
    public List<Medicine> getMedicinesByNameAndUses(String name, String uses) {
        // Log to debug
        System.out.println("Searching for medicines with name: " + name + " and uses: " + uses);

        // Handle default values
        if (name == null || name.isEmpty()) {
            name = "";
        }
        if (uses == null || uses.isEmpty()) {
            uses = "";
        }

        // Call the repository method to fetch pharmacies
        return medicineRepository.findByNameIgnoreCaseAndUsesIgnoreCase(name, uses);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    public Page<Medicine> getMedicines(Pageable pageable) {
        return medicineRepository.findAll(pageable);  // Find medicines with pagination
    }
}
