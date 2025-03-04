package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "pharmacy")
@Data

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("pharmacie")
    private String name;
    @JsonProperty("lien")
    private String link;
    @JsonProperty("quartier")
    private String street;
    @JsonProperty("adresse")
    private String address;
    @JsonProperty("coordonnee")
    private String coordinate;
    @JsonProperty("telephone")
    private  String phone ;
    @JsonProperty("etat")
    private String status;
    @JsonProperty("cle")
    @Column(name = "api_key")  // Optional: Use a custom column name in the database
    private String apiKey;
    // Relation Many-to-Many avec quantité via PharmacyMedicine
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyMedicine> pharmacyMedicines = new ArrayList<>();

}
