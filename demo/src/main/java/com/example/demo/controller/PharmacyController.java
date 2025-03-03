package com.example.demo.controller;

import com.example.demo.model.Pharmacy;
import com.example.demo.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController


@RequestMapping("/api/Dutypharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    // This endpoint will fetch all pharmacies from the service
    @GetMapping
    public List<Pharmacy> getPharmacies() {
        return
                pharmacyService.getAllPharmacies();
    }





    @PostMapping("/filter")
    public List<Pharmacy> getPharmaBasedOnCityAndStatus(@RequestBody Map<String, String> filters) {
        String street = filters.get("street");
        String status = filters.get("status");

        System.out.println("Filtre reçu - Rue: " + street + ", Statut: " + status);


        List<Pharmacy> pharmacies = pharmacyService.getPharmaciesByCityAndStatus(street, status);

        System.out.println("Nombre de pharmacies trouvées: " + pharmacies.size());

        return pharmacies;
    }





}
