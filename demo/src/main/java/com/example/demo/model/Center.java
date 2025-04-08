package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "DyalisisCenter")
@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("city")
    private String city;
    @JsonProperty("address")
    private String address;
    @JsonProperty("website")
    private String website;
    @JsonProperty("opening_hours")
    private String opening_hours;
    @JsonProperty("days_open")
    private  String days_open ;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("type")
    private String type;

    @JsonProperty("capacity")
    private Integer capacity;

}
