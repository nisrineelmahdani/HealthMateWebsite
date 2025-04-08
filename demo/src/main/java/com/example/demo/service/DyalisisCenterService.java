package com.example.demo.service;

import com.example.demo.model.Center;
import com.example.demo.model.Pharmacy;
import com.example.demo.repository.DyalisisCenterRepository;
import com.example.demo.repository.PharmacyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Service
public class DyalisisCenterService {



    private final  DyalisisCenterRepository dyalisisCenterRepository;





    public DyalisisCenterService(DyalisisCenterRepository dyalisisCenterRepository) {
        this.dyalisisCenterRepository = dyalisisCenterRepository;
    }

  /* public void loadCentersFromJson() {
        try {
            // Charger le fichier JSON depuis le dossier resources/
            ClassPathResource resource = new ClassPathResource("dyalise.json");
            InputStream inputStream = resource.getInputStream();

            // Convertir JSON en liste de Medicine
            ObjectMapper objectMapper = new ObjectMapper();
            List<Center> centers = objectMapper.readValue(inputStream, new TypeReference<List<Center>>() {});

            // Vérifier les données avant insertion
            if (centers.isEmpty()) {
                System.err.println("❌ Aucune donnée trouvée dans le fichier JSON !");
                return;
            }


           dyalisisCenterRepository.saveAll(centers);
            System.out.println("✅ Centers loaded successfully into the database!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Erreur lors du chargement du JSON", e);
        }
    }


    @PostConstruct
    public void init() {
        loadCentersFromJson();
    }
*/

    public List<Center> getAllCenters() {
         return dyalisisCenterRepository.findAll();

    }
}
