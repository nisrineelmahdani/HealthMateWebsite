package com.example.demo.controller;

import com.example.demo.model.PharmacyMedicine;
import com.example.demo.model.PharmacyMedicineId;
import com.example.demo.repository.PharmacyMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pharmacy-medicines")
public class PharmacyMedicineController {

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;

    @GetMapping
    public List<PharmacyMedicine> getAllPharmacyMedicines() {
        return pharmacyMedicineRepository.findAll();
    }

    @GetMapping(params = "medicineId")
    public List<PharmacyMedicine> getPharmacyMedicinesByMedicineId(@RequestParam Long medicineId) {
        return pharmacyMedicineRepository.findByMedicineId(medicineId);
    }

    @GetMapping(params = {"medicineId", "pharmacyId"})
    public List<PharmacyMedicine> getPharmacyMedicinesByMedicineAndPharmacy(
            @RequestParam Long medicineId,
            @RequestParam Long pharmacyId) {
        return pharmacyMedicineRepository.findByMedicineIdAndPharmacyId(medicineId, pharmacyId);
    }

    @GetMapping("/{pharmacyId}/{medicineId}")
    public ResponseEntity<PharmacyMedicine> getPharmacyMedicineById(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId) {

        // Create PharmacyMedicineId using both pharmacyId and medicineId
        PharmacyMedicineId pharmacyMedicineId = new PharmacyMedicineId(pharmacyId, medicineId);

        return pharmacyMedicineRepository.findById(pharmacyMedicineId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PharmacyMedicine createPharmacyMedicine(@RequestBody PharmacyMedicine pharmacyMedicine) {
        return pharmacyMedicineRepository.save(pharmacyMedicine);
    }

    @PutMapping("/{pharmacyId}/{medicineId}")
    public ResponseEntity<PharmacyMedicine> updatePharmacyMedicine(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId,
            @RequestBody PharmacyMedicine pharmacyMedicine) {

        // Use composite key to find the entity
        PharmacyMedicineId pharmacyMedicineId = new PharmacyMedicineId(pharmacyId, medicineId);
        return pharmacyMedicineRepository.findById(pharmacyMedicineId)
                .map(existingPharmacyMedicine -> {
                    existingPharmacyMedicine.setQuantity(pharmacyMedicine.getQuantity());
                    return ResponseEntity.ok(pharmacyMedicineRepository.save(existingPharmacyMedicine));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{pharmacyId}/{medicineId}")
    public ResponseEntity<PharmacyMedicine> updatePharmacyMedicineQuantity(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId,
            @RequestBody PharmacyMedicine pharmacyMedicine) {

        // Use composite key to find the entity
        PharmacyMedicineId pharmacyMedicineId = new PharmacyMedicineId(pharmacyId, medicineId);
        return pharmacyMedicineRepository.findById(pharmacyMedicineId)
                .map(existingPharmacyMedicine -> {
                    existingPharmacyMedicine.setQuantity(pharmacyMedicine.getQuantity());
                    return ResponseEntity.ok(pharmacyMedicineRepository.save(existingPharmacyMedicine));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{pharmacyId}/{medicineId}")
    public ResponseEntity<Void> deletePharmacyMedicine(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId) {

        PharmacyMedicineId pharmacyMedicineId = new PharmacyMedicineId(pharmacyId, medicineId);
        return pharmacyMedicineRepository.findById(pharmacyMedicineId)
                .map(pharmacyMedicine -> {
                    pharmacyMedicineRepository.delete(pharmacyMedicine);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
