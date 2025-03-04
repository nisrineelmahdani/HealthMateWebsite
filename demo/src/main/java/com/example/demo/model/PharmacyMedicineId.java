package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.*;
@Embeddable
public class PharmacyMedicineId implements Serializable {

    @Column(name = "pharmacy_id")
    private Long pharmacyId;

    @Column(name = "medicine_id")
    private Long medicineId;

    // Default constructor, getters and setters, hashCode, and equals
    public PharmacyMedicineId() {}

    public PharmacyMedicineId(Long pharmacyId, Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyMedicineId that = (PharmacyMedicineId) o;
        return pharmacyId.equals(that.pharmacyId) && medicineId.equals(that.medicineId);
    }

    @Override
    public int hashCode() {
        return 31 * pharmacyId.hashCode() + medicineId.hashCode();
    }
}
