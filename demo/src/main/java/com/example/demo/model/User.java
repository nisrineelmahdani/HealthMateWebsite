package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name= "user" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data

@Getter @Setter
@AllArgsConstructor

public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private  String lastName;

    @Column(name= "password")
    private String password;

    private String email;
    public User(){

    }
@ManyToMany(fetch =FetchType.EAGER , cascade = CascadeType.ALL)
@JoinTable(
        name= "user_roles" ,
        joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "id")
)
    private Collection<Role> roles;

    public <T> User(String firstName, String lastName, String email, String password, List<Role> roleUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles= roleUser;
    }
}
