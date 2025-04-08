package com.example.demo.controller;

import com.example.demo.model.Medicine;


import com.example.demo.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // Endpoint to fetch all medicines without pagination
    @GetMapping
    public ResponseEntity<?> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    // Endpoint to search medicines by name
    @PostMapping("/filter")
    public ResponseEntity<?> searchMedicines(@RequestBody Medicine searchCriteria) {
        List<Medicine> medicines = medicineService.searchMedicinesByName(searchCriteria.getName());
        return ResponseEntity.ok(medicines);
    }
}
