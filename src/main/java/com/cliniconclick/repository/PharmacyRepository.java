package com.cliniconclick.repository;

import com.cliniconclick.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    
    Optional<Pharmacy> findByUserUsername(String username);
    
    List<Pharmacy> findByActiveTrue();
    
    Optional<Pharmacy> findByLicenseNumber(String licenseNumber);
}
