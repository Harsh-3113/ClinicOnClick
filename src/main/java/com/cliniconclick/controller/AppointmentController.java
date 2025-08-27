package com.cliniconclick.controller;

import com.cliniconclick.entity.Appointment;
import com.cliniconclick.entity.Doctor;
import com.cliniconclick.service.AppointmentService;
import com.cliniconclick.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.RuntimeException;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

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
    public String bookAppointment(@ModelAttribute Appointment appointment) {
        try {
            appointmentService.createAppointment(appointment);
            return "redirect:/appointments?success=true";
        } catch (RuntimeException e) {
            return "redirect:/appointments/book?error=" + e.getMessage();
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
