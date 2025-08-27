package com.cliniconclick.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotBlank(message = "Pharmacy name is required")
    private String pharmacyName;
    
    private String licenseNumber;
    
    private String address;
    
    private String phoneNumber;
    
    private String email;
    
    private String description;
    
    private String imageUrl;
    
    private boolean active = true;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Medicine> medicines = new HashSet<>();
    
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
    public Pharmacy() {}
    
    public Pharmacy(User user, String pharmacyName, String licenseNumber) {
        this.user = user;
        this.pharmacyName = pharmacyName;
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
    
    public String getPharmacyName() {
        return pharmacyName;
    }
    
    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
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
    
    public Set<Medicine> getMedicines() {
        return medicines;
    }
    
    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
    
    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", pharmacyName='" + pharmacyName + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", active=" + active +
                '}';
    }
}
