package com.cliniconclick.config;

import com.cliniconclick.entity.*;
import com.cliniconclick.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no users exist
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        System.out.println("🚀 Initializing ClinicOnClick with sample data...");

        // Create Admin User
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@cliniconclick.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFirstName("System");
        admin.setLastName("Administrator");
        // roles removed
        admin.setPhoneNumber("+1-555-0001");
        userRepository.save(admin);

        // Create Doctor Users
        User doctor1 = new User();
        doctor1.setUsername("dr.smith");
        doctor1.setEmail("dr.smith@cliniconclick.com");
        doctor1.setPassword(passwordEncoder.encode("doctor123"));
        doctor1.setFirstName("John");
        doctor1.setLastName("Smith");
        // roles removed
        doctor1.setPhoneNumber("+1-555-0002");
        userRepository.save(doctor1);

        User doctor2 = new User();
        doctor2.setUsername("dr.johnson");
        doctor2.setEmail("dr.johnson@cliniconclick.com");
        doctor2.setPassword(passwordEncoder.encode("doctor123"));
        doctor2.setFirstName("Sarah");
        doctor2.setLastName("Johnson");
        // roles removed
        doctor2.setPhoneNumber("+1-555-0003");
        userRepository.save(doctor2);

        // Create Patient Users
        User patient1 = new User();
        patient1.setUsername("john.doe");
        patient1.setEmail("john.doe@email.com");
        patient1.setPassword(passwordEncoder.encode("patient123"));
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        // roles removed
        patient1.setPhoneNumber("+1-555-0004");
        userRepository.save(patient1);

        User patient2 = new User();
        patient2.setUsername("jane.smith");
        patient2.setEmail("jane.smith@email.com");
        patient2.setPassword(passwordEncoder.encode("patient123"));
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");
        // roles removed
        patient2.setPhoneNumber("+1-555-0005");
        userRepository.save(patient2);

        // Create Pharmacy User
        User pharmacist = new User();
        pharmacist.setUsername("pharmacy.main");
        pharmacist.setEmail("pharmacy@cliniconclick.com");
        pharmacist.setPassword(passwordEncoder.encode("pharmacy123"));
        pharmacist.setFirstName("Main");
        pharmacist.setLastName("Pharmacy");
        // roles removed
        pharmacist.setPhoneNumber("+1-555-0006");
        userRepository.save(pharmacist);

        // Create Doctor Profiles
        Doctor drSmith = new Doctor();
        drSmith.setUser(doctor1);
        drSmith.setSpecialization("Cardiology");
        drSmith.setLicenseNumber("MD12345");
        drSmith.setExperience("15 years");
        drSmith.setEducation("Harvard Medical School");
        drSmith.setBio("Experienced cardiologist with expertise in interventional cardiology and heart failure management.");
        drSmith.setImageUrl("/images/doctors/dr-smith.jpg");
        doctorRepository.save(drSmith);

        Doctor drJohnson = new Doctor();
        drJohnson.setUser(doctor2);
        drJohnson.setSpecialization("Pediatrics");
        drJohnson.setLicenseNumber("MD67890");
        drJohnson.setExperience("12 years");
        drJohnson.setEducation("Stanford Medical School");
        drJohnson.setBio("Dedicated pediatrician committed to providing comprehensive care for children of all ages.");
        drJohnson.setImageUrl("/images/doctors/dr-johnson.jpg");
        doctorRepository.save(drJohnson);

        // Create Pharmacy
        Pharmacy mainPharmacy = new Pharmacy();
        mainPharmacy.setUser(pharmacist);
        mainPharmacy.setPharmacyName("Main Street Pharmacy");
        mainPharmacy.setLicenseNumber("PH12345");
        mainPharmacy.setAddress("123 Main Street, Healthcare City, HC 12345");
        mainPharmacy.setPhoneNumber("+1-555-0006");
        mainPharmacy.setEmail("pharmacy@cliniconclick.com");
        mainPharmacy.setDescription("Your trusted neighborhood pharmacy providing quality healthcare products and services.");
        mainPharmacy.setImageUrl("/images/pharmacy/main-pharmacy.jpg");
        pharmacyRepository.save(mainPharmacy);

        // Create Medicines
        Medicine medicine1 = new Medicine();
        medicine1.setName("Aspirin 100mg");
        medicine1.setDescription("Pain reliever and blood thinner");
        medicine1.setManufacturer("Bayer");
        medicine1.setPrice(new BigDecimal("9.99"));
        medicine1.setDosage("100mg tablet");
        medicine1.setCategory("Pain Relief");
        medicine1.setPrescriptionRequired(false);
        medicine1.setStockQuantity(100);
        medicine1.setImageUrl("/images/medicines/aspirin.jpg");
        medicine1.setPharmacy(mainPharmacy);
        medicineRepository.save(medicine1);

        Medicine medicine2 = new Medicine();
        medicine2.setName("Amoxicillin 500mg");
        medicine2.setDescription("Antibiotic for bacterial infections");
        medicine2.setManufacturer("Pfizer");
        medicine2.setPrice(new BigDecimal("25.99"));
        medicine2.setDosage("500mg capsule");
        medicine2.setCategory("Antibiotics");
        medicine2.setPrescriptionRequired(true);
        medicine2.setStockQuantity(50);
        medicine2.setImageUrl("/images/medicines/amoxicillin.jpg");
        medicine2.setPharmacy(mainPharmacy);
        medicineRepository.save(medicine2);

        Medicine medicine3 = new Medicine();
        medicine3.setName("Vitamin D3 1000IU");
        medicine3.setDescription("Supports bone health and immune system");
        medicine3.setManufacturer("Nature Made");
        medicine3.setPrice(new BigDecimal("15.99"));
        medicine3.setDosage("1000IU softgel");
        medicine3.setCategory("Vitamins");
        medicine3.setPrescriptionRequired(false);
        medicine3.setStockQuantity(75);
        medicine3.setImageUrl("/images/medicines/vitamin-d3.jpg");
        medicine3.setPharmacy(mainPharmacy);
        medicineRepository.save(medicine3);

        // Create Appointments
        Appointment appointment1 = new Appointment();
        appointment1.setUser(patient1);
        appointment1.setDoctor(drSmith);
        appointment1.setAppointmentDateTime(LocalDateTime.now().plusDays(7).withHour(10).withMinute(0));
        appointment1.setStatus(AppointmentStatus.SCHEDULED);
        appointment1.setReason("Annual heart checkup");
        appointment1.setNotes("Patient requested morning appointment");
        appointmentRepository.save(appointment1);

        Appointment appointment2 = new Appointment();
        appointment2.setUser(patient2);
        appointment2.setDoctor(drJohnson);
        appointment2.setAppointmentDateTime(LocalDateTime.now().plusDays(5).withHour(14).withMinute(30));
        appointment2.setStatus(AppointmentStatus.CONFIRMED);
        appointment2.setReason("Child vaccination");
        appointment2.setNotes("Bring vaccination record");
        appointmentRepository.save(appointment2);

        System.out.println("✅ Sample data initialized successfully!");
        System.out.println("👥 Users created: " + userRepository.count());
        System.out.println("👨‍⚕️ Doctors created: " + doctorRepository.count());
        System.out.println("💊 Pharmacy created: " + pharmacyRepository.count());
        System.out.println("💊 Medicines created: " + medicineRepository.count());
        System.out.println("📅 Appointments created: " + appointmentRepository.count());
        System.out.println("\n🔑 Default Login Credentials:");
        System.out.println("Admin: admin / admin123");
        System.out.println("Doctor: dr.smith / doctor123");
        System.out.println("Patient: john.doe / patient123");
        System.out.println("Pharmacy: pharmacy.main / pharmacy123");
    }
}
