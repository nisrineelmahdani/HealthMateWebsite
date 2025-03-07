package com.example.demo.service;

import com.example.demo.model.PharmacyMedicine;
import com.example.demo.repository.PharmacyMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PharmacyMedicineService {

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;

    // Create a new PharmacyMedicine association
    public PharmacyMedicine createPharmacyMedicine(PharmacyMedicine pharmacyMedicine) {
        return pharmacyMedicineRepository.save(pharmacyMedicine);
    }

    // Get all PharmacyMedicine associations
    public List<PharmacyMedicine> getAllPharmacyMedicines() {
        return pharmacyMedicineRepository.findAll();
    }

    // Get a PharmacyMedicine by pharmacyId and medicineId
    public PharmacyMedicine getPharmacyMedicine(Long pharmacyId, Long medicineId) {
        return pharmacyMedicineRepository.findById_PharmacyIdAndId_MedicineId(pharmacyId, medicineId);
    }

    // Update the quantity of a medicine in a pharmacy
    public PharmacyMedicine updateQuantity(Long pharmacyId, Long medicineId, int quantity) {
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository.findById_PharmacyIdAndId_MedicineId(pharmacyId, medicineId);
        if (pharmacyMedicine != null) {
            pharmacyMedicine.setQuantity(quantity);
            return pharmacyMedicineRepository.save(pharmacyMedicine);
        }
        return null;
    }

    // Delete a PharmacyMedicine association
    public boolean deletePharmacyMedicine(Long pharmacyId, Long medicineId) {
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository.findById_PharmacyIdAndId_MedicineId(pharmacyId, medicineId);
        if (pharmacyMedicine != null) {
            pharmacyMedicineRepository.delete(pharmacyMedicine);
            return true;
        }
        return false;
    }
}
