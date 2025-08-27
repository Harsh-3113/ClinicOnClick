package com.cliniconclick.repository;

import com.cliniconclick.entity.Appointment;
import com.cliniconclick.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    List<Appointment> findByUserId(Long userId);
    
    List<Appointment> findByDoctorId(Long doctorId);
    
    List<Appointment> findByStatus(AppointmentStatus status);
    
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime start, LocalDateTime end);
    
    List<Appointment> findByUserIdAndStatus(Long userId, AppointmentStatus status);
    
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, AppointmentStatus status);
}
