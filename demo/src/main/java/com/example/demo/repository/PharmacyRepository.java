package com.example.demo.repository;

import com.example.demo.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long >

{
    List<Pharmacy> findByStreetIgnoreCaseAndStatusIgnoreCase(String street, String status);
    Optional<Pharmacy> findByName(String name);

    Optional<Pharmacy> findByApiKey(String apiKey);

    List<Pharmacy> findByStreetAndStatus(String street, String status);
}
