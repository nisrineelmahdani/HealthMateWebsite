package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pharmacy_medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyMedicine {
    @EmbeddedId
    private PharmacyMedicineId id;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id", insertable = false, updatable = false)
    private Pharmacy pharmacy;


    @ManyToOne
    @JoinColumn(name = "medicine_id", insertable = false, updatable = false)
    private Medicine medicine;

    private int quantity;
}

