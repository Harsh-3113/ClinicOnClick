package com.cliniconclick.service;

import com.cliniconclick.entity.Appointment;
import com.cliniconclick.entity.AppointmentStatus;
import com.cliniconclick.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    public Appointment createAppointment(Appointment appointment) {
        // Set default status if not provided
        if (appointment.getStatus() == null) {
            appointment.setStatus(AppointmentStatus.SCHEDULED);
        }
        
        // Validate appointment time
        if (appointment.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Appointment time cannot be in the past");
        }
        
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        appointment.setAppointmentDateTime(appointmentDetails.getAppointmentDateTime());
        appointment.setReason(appointmentDetails.getReason());
        appointment.setNotes(appointmentDetails.getNotes());
        appointment.setStatus(appointmentDetails.getStatus());
        
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getUpcomingAppointments(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        return appointmentRepository.findByUserIdAndStatus(userId, AppointmentStatus.SCHEDULED)
                .stream()
                .filter(appointment -> appointment.getAppointmentDateTime().isAfter(now))
                .toList();
    }

    public List<Appointment> getAppointmentsInDateRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentDateTimeBetween(start, end);
    }
}
