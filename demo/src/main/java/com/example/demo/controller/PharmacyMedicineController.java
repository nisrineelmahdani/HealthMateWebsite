package com.example.demo.controller;

import com.example.demo.model.PharmacyMedicine;
import com.example.demo.service.PharmacyMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // New import for Jakarta validation

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy-medicines")
public class PharmacyMedicineController {

    @Autowired
    private PharmacyMedicineService pharmacyMedicineService;

    // Endpoint to create a Pharmacy-Medicine association
    @PostMapping
    public ResponseEntity<PharmacyMedicine> addPharmacyMedicine(@Valid @RequestBody PharmacyMedicine pharmacyMedicine) {
        PharmacyMedicine createdPharmacyMedicine = pharmacyMedicineService.createPharmacyMedicine(pharmacyMedicine);
        return new ResponseEntity<>(createdPharmacyMedicine, HttpStatus.CREATED);
    }

    // Endpoint to get all Pharmacy-Medicine associations
    @GetMapping
    public ResponseEntity<List<PharmacyMedicine>> getAllPharmacyMedicines() {
        List<PharmacyMedicine> pharmacyMedicines = pharmacyMedicineService.getAllPharmacyMedicines();
        return new ResponseEntity<>(pharmacyMedicines, HttpStatus.OK);
    }

    // Endpoint to get a Pharmacy-Medicine association by Pharmacy and Medicine IDs
    @GetMapping("/pharmacy/{pharmacyId}/medicine/{medicineId}")
    public ResponseEntity<PharmacyMedicine> getPharmacyMedicine(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId) {
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineService.getPharmacyMedicine(pharmacyId, medicineId);
        if (pharmacyMedicine == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pharmacyMedicine, HttpStatus.OK);
    }

    // Endpoint to update the quantity of a medicine in a pharmacy
    @PutMapping("/pharmacy/{pharmacyId}/medicine/{medicineId}")
    public ResponseEntity<PharmacyMedicine> updateQuantity(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId,
            @RequestParam int quantity) {
        PharmacyMedicine updatedPharmacyMedicine = pharmacyMedicineService.updateQuantity(pharmacyId, medicineId, quantity);
        if (updatedPharmacyMedicine == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPharmacyMedicine, HttpStatus.OK);
    }

    // Endpoint to delete a Pharmacy-Medicine association
    @DeleteMapping("/pharmacy/{pharmacyId}/medicine/{medicineId}")
    public ResponseEntity<Void> deletePharmacyMedicine(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId) {
        boolean isDeleted = pharmacyMedicineService.deletePharmacyMedicine(pharmacyId, medicineId);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
