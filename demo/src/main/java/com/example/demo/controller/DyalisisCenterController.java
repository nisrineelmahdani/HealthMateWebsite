package com.example.demo.controller;

import com.example.demo.model.Center;
import com.example.demo.model.Pharmacy;
import com.example.demo.service.DyalisisCenterService;
import com.example.demo.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController


@RequestMapping("/api/DyalisisCenters")
public class DyalisisCenterController {
    @Autowired
    private DyalisisCenterService dyalisisCenterService;






    @GetMapping
    public List<Center> getCenters() {
        return
                dyalisisCenterService.getAllCenters();
    }
}
