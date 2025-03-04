package com.example.demo;

import com.example.demo.model.Pharmacy;
import com.example.demo.repository.PharmacyRepository;
import com.example.demo.service.PharmacyService;
import com.example.demo.utils.JsonFixer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}




}
