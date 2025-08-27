package com.cliniconclick.service;

import com.cliniconclick.entity.Medicine;
import com.cliniconclick.entity.Pharmacy;
import com.cliniconclick.repository.MedicineRepository;
import com.cliniconclick.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Pharmacy> getActivePharmacies() {
        return pharmacyRepository.findByActiveTrue();
    }

    public Optional<Pharmacy> getPharmacyById(Long id) {
        return pharmacyRepository.findById(id);
    }

    public List<Medicine> getMedicinesForPharmacy(Long pharmacyId) {
        return medicineRepository.findByPharmacyId(pharmacyId);
    }
}


