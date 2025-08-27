package com.cliniconclick.controller;

import com.cliniconclick.entity.Medicine;
import com.cliniconclick.entity.Pharmacy;
import com.cliniconclick.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping
    public String listPharmacies(Model model) {
        List<Pharmacy> pharmacies = pharmacyService.getActivePharmacies();
        model.addAttribute("pharmacies", pharmacies);
        model.addAttribute("title", "Pharmacy - ClinicOnClick");
        return "pharmacy/list";
    }

    @GetMapping("/{id}")
    public String viewPharmacy(@PathVariable Long id, Model model) {
        Pharmacy pharmacy = pharmacyService.getPharmacyById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
        List<Medicine> medicines = pharmacyService.getMedicinesForPharmacy(id);
        model.addAttribute("pharmacy", pharmacy);
        model.addAttribute("medicines", medicines);
        model.addAttribute("title", "Pharmacy Details - ClinicOnClick");
        return "pharmacy/view";
    }
}


