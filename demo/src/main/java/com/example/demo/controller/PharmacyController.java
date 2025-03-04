package com.example.demo.controller;

import com.example.demo.model.Medicine;
import com.example.demo.model.Pharmacy;
import com.example.demo.model.PharmacyMedicine;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PharmacyMedicineRepository;
import com.example.demo.repository.PharmacyRepository;
import com.example.demo.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;

    // Ajouter un médicament avec une quantité à une pharmacie
    @PostMapping("/{pharmacyId}/medicines/{medicineId}")
    public ResponseEntity<PharmacyMedicine> addMedicineToPharmacyWithQuantity(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId,
            @RequestParam int quantity) {

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);

        if (pharmacy == null || medicine == null) {
            return ResponseEntity.notFound().build();
        }

        PharmacyMedicine pharmacyMedicine = new PharmacyMedicine();
        pharmacyMedicine.setPharmacy(pharmacy);
        pharmacyMedicine.setMedicine(medicine);
        pharmacyMedicine.setQuantity(quantity);

        pharmacyMedicineRepository.save(pharmacyMedicine);

        return ResponseEntity.ok(pharmacyMedicine);
    }
    @GetMapping("/{pharmacyId}/medicines")
    public ResponseEntity<List<PharmacyMedicine>> getMedicinesByPharmacyWithQuantity(@PathVariable Long pharmacyId) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);

        if (pharmacy == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pharmacy.getPharmacyMedicines());
    }



}
