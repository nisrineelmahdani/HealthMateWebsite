package com.example.demo.repository;

import com.example.demo.model.PharmacyMedicine;
import com.example.demo.model.PharmacyMedicineId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface PharmacyMedicineRepository extends JpaRepository<PharmacyMedicine, PharmacyMedicineId> {

    PharmacyMedicine findById_PharmacyIdAndId_MedicineId(Long pharmacyId, Long medicineId);

    List<PharmacyMedicine> findByMedicineId(Long medicineId);

    List<PharmacyMedicine> findByMedicineIdAndPharmacyId(Long medicineId, Long pharmacyId);
}
