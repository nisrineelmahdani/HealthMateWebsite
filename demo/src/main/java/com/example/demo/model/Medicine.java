package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("medicine_name")
    private String name;  // Corresponding to the "medicine_name" field in the JSON

    @Column(length = 1000)
    private String composition;

    @Column(length = 1000)
    private String uses;

    @JsonProperty("side_effects")
    @Column(columnDefinition = "TEXT") // To store long text data
    private String sideEffects;

    private String manufacturer;

    @JsonProperty("excellent_review")
    private int excellentReview;

    @JsonProperty("average_review")
    private int averageReview;

    @JsonProperty("poor_review")
    private int poorReview;

    @JsonProperty("image_url")
    private String imageUrl;


}
