package com.cliniconclick.entity;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "doctor_availabilities")
public class DoctorAvailability {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    
    private LocalTime startTime;
    
    private LocalTime endTime;
    
    private boolean available = true;
    
    // Constructors
    public DoctorAvailability() {}
    
    public DoctorAvailability(Doctor doctor, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.doctor = doctor;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    @Override
    public String toString() {
        return "DoctorAvailability{" +
                "id=" + id +
                ", doctor=" + doctor.getUser().getUsername() +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", available=" + available +
                '}';
    }
}
