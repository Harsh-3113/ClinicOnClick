package com.cliniconclick.controller;

import com.cliniconclick.entity.Doctor;
import com.cliniconclick.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("title", "Doctors - ClinicOnClick");
        return "doctors/list";
    }

    @GetMapping("/{id}")
    public String viewDoctor(@PathVariable Long id, Model model) {
        try {
            Doctor doctor = doctorService.getDoctorById(id)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            model.addAttribute("doctor", doctor);
            model.addAttribute("title", "Doctor Profile - ClinicOnClick");
            return "doctors/view";
        } catch (RuntimeException e) {
            return "redirect:/doctors?error=doctor_not_found";
        }
    }

    @GetMapping("/specialization/{specialization}")
    public String doctorsBySpecialization(@PathVariable String specialization, Model model) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
        model.addAttribute("doctors", doctors);
        model.addAttribute("specialization", specialization);
        model.addAttribute("title", "Doctors - " + specialization + " - ClinicOnClick");
        return "doctors/specialization";
    }

    @GetMapping("/available")
    public String availableDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAvailableDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("title", "Available Doctors - ClinicOnClick");
        return "doctors/available";
    }

    @GetMapping("/search")
    public String searchDoctors(@RequestParam(required = false) String query,
                               @RequestParam(required = false) String specialization,
                               Model model) {
        List<Doctor> doctors;
        if (query != null && !query.trim().isEmpty()) {
            doctors = doctorService.searchDoctors(query);
        } else if (specialization != null && !specialization.trim().isEmpty()) {
            doctors = doctorService.getDoctorsBySpecialization(specialization);
        } else {
            doctors = doctorService.getAllDoctors();
        }
        
        model.addAttribute("doctors", doctors);
        model.addAttribute("query", query);
        model.addAttribute("specialization", specialization);
        model.addAttribute("title", "Search Doctors - ClinicOnClick");
        return "doctors/search";
    }
}
