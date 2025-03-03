package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "role" )
@Data

@Getter
@Setter
@AllArgsConstructor

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role(String roleUser) {
        super();
        this.name= name;
    }
    public  Role(){

    }
}
