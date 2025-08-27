package com.cliniconclick.repository;

import com.cliniconclick.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUserUsername(String username);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByAvailableTrue();
}
