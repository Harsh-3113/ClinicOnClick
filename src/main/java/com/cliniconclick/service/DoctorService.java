package com.cliniconclick.service;

import com.cliniconclick.entity.Doctor;
import com.cliniconclick.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // UserRepository no longer used for role checks

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> getDoctorByUsername(String username) {
        return doctorRepository.findByUserUsername(username);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    public List<Doctor> getAvailableDoctors() {
        return doctorRepository.findByAvailableTrue();
    }

    public List<Doctor> searchDoctors(String query) {
        // This could be enhanced with more sophisticated search logic
        List<Doctor> allDoctors = doctorRepository.findAll();
        return allDoctors.stream()
                .filter(doctor -> 
                    doctor.getUser().getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                    doctor.getUser().getLastName().toLowerCase().contains(query.toLowerCase()) ||
                    doctor.getSpecialization().toLowerCase().contains(query.toLowerCase())
                )
                .toList();
    }



    public Doctor createDoctor(Doctor doctor) {
        if (doctor.getUser() == null) {
            throw new RuntimeException("Doctor must be linked to a user");
        }
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setExperience(doctorDetails.getExperience());
        doctor.setEducation(doctorDetails.getEducation());
        doctor.setBio(doctorDetails.getBio());
        doctor.setImageUrl(doctorDetails.getImageUrl());
        doctor.setAvailable(doctorDetails.isAvailable());
        
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }



    public List<String> getAllSpecializations() {
        return doctorRepository.findAll().stream()
                .map(Doctor::getSpecialization)
                .distinct()
                .toList();
    }

    public void toggleAvailability(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        doctor.setAvailable(!doctor.isAvailable());
        doctorRepository.save(doctor);
    }
}
