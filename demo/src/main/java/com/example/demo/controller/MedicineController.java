package com.example.demo.controller;

import com.example.demo.model.Medicine;
import com.example.demo.model.Pharmacy;
import com.example.demo.service.MedicineService;
import com.example.demo.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController


@RequestMapping("/api/Medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;


    @GetMapping
    public List<Medicine> getMedicines() {
        return
                medicineService.getAllMedicines();
    }

    @GetMapping("/load")
    public String loadMedicines() {
        medicineService.loadMedicinesFromJson();
        return "✅ Medicines have been loaded!";
    }




    @PostMapping("/filter")
    public List<Medicine> getMedBasedOnNameAndUses(@RequestBody Map<String, String> filters) {
        String name = filters.get("name");
        String uses = filters.get("Uses");

        System.out.println("Filtre reçu - name: " + name + ", uses: " + uses);


        List<Medicine> medicines = medicineService.getMedicinesByNameAndUses(name, uses);

        System.out.println("Nombre de pharmacies trouvées: " + medicines.size());

        return medicines;
    }
}
