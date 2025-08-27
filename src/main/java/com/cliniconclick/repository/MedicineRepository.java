package com.cliniconclick.repository;

import com.cliniconclick.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
    List<Medicine> findByCategory(String category);
    
    List<Medicine> findByAvailableTrue();
    
    List<Medicine> findByPrescriptionRequired(boolean prescriptionRequired);
    
    List<Medicine> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Medicine> findByPharmacyId(Long pharmacyId);
    
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
