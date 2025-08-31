package com.cliniconclick.controller;

import com.cliniconclick.entity.Appointment;
import com.cliniconclick.entity.Doctor;
import com.cliniconclick.service.AppointmentService;
import com.cliniconclick.service.DoctorService;
import com.cliniconclick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;
import java.lang.RuntimeException;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        model.addAttribute("title", "Appointments - ClinicOnClick");
        return "appointments/list";
    }

    @GetMapping("/book")
    public String bookAppointmentForm(Model model) {
        List<Doctor> availableDoctors = doctorService.getAvailableDoctors();
        model.addAttribute("doctors", availableDoctors);
        model.addAttribute("title", "Book Appointment - ClinicOnClick");
        return "appointments/book";
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestParam("doctor.id") Long doctorId,
                                  @RequestParam("appointmentDate") String appointmentDate,
                                  @RequestParam("appointmentTime") String appointmentTime,
                                  @RequestParam("reason") String reason,
                                  @RequestParam(value = "notes", required = false) String notes,
                                  Principal principal,
                                  RedirectAttributes redirectAttributes) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }

            // Load current user and selected doctor
            Doctor doctor = doctorService.getDoctorById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Selected doctor not found"));
            com.cliniconclick.entity.User user = userService.getUserByUsername(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Logged in user not found"));

            // Combine date and time into LocalDateTime
            LocalDate date = LocalDate.parse(appointmentDate);
            LocalTime time = LocalTime.parse(appointmentTime);
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            // Build appointment explicitly
            Appointment appointment = new Appointment();
            appointment.setDoctor(doctor);
            appointment.setUser(user);

            appointment.setAppointmentDateTime(dateTime);
            appointment.setReason(reason);
            appointment.setNotes(notes);

            appointmentService.createAppointment(appointment);
            redirectAttributes.addFlashAttribute("success", "Appointment booked successfully");
            return "redirect:/appointments";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/appointments/book";
        }
    }

    @GetMapping("/{id}")
    public String viewAppointment(@PathVariable Long id, Model model) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
            model.addAttribute("appointment", appointment);
            model.addAttribute("title", "Appointment Details - ClinicOnClick");
            return "appointments/view";
        } catch (RuntimeException e) {
            return "redirect:/appointments?error=appointment_not_found";
        }
    }

    @PostMapping("/{id}/cancel")
    public String cancelAppointment(@PathVariable Long id) {
        try {
            appointmentService.cancelAppointment(id);
            return "redirect:/appointments?cancelled=true";
        } catch (RuntimeException e) {
            return "redirect:/appointments?error=appointment_not_found";
        }
    }
}
