package com.example.demo.model;

import com.example.demo.model.Medicine;
import com.example.demo.model.Pharmacy;
import jakarta.persistence.*;

@Entity
@Table(name = "pharmacy_medicines")
public class PharmacyMedicine {

    @EmbeddedId
    private PharmacyMedicineId id;

    @ManyToOne
    @MapsId("pharmacyId")
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @MapsId("medicineId")
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(nullable = false)
    private Integer quantity;

    // Getters and setters

    public PharmacyMedicineId getId() {
        return id;
    }

    public void setId(PharmacyMedicineId id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
