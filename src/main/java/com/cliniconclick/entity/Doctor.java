package com.cliniconclick.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotBlank(message = "Specialization is required")
    private String specialization;
    
    private String licenseNumber;
    
    private String experience;
    
    private String education;
    
    private String bio;
    
    private String imageUrl;
    
    private boolean available = true;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DoctorAvailability> availabilities = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Doctor() {}
    
    public Doctor(User user, String specialization, String licenseNumber) {
        this.user = user;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    public String getExperience() {
        return experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public String getEducation() {
        return education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Set<Appointment> getAppointments() {
        return appointments;
    }
    
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    public Set<DoctorAvailability> getAvailabilities() {
        return availabilities;
    }
    
    public void setAvailabilities(Set<DoctorAvailability> availabilities) {
        this.availabilities = availabilities;
    }
    
    @Override
    public String toString() {
        String username = (user != null ? user.getUsername() : "null");
        return "Doctor{" +
                "id=" + id +
                ", user=" + username +
                ", specialization='" + (specialization != null ? specialization : "null") + '\'' +
                ", licenseNumber='" + (licenseNumber != null ? licenseNumber : "null") + '\'' +
                ", available=" + available +
                '}';
    }
}
