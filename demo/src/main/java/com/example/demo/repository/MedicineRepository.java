package com.example.demo.repository;

import com.example.demo.model.Medicine;
import com.example.demo.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository  extends JpaRepository<Medicine, Long> {
    List<Medicine> findByNameIgnoreCaseAndUsesIgnoreCase(String name, String uses);
}
