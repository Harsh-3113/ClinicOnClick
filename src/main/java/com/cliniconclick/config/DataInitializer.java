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

    // Create Admin User (Indian details)
    User admin = new User();
    admin.setUsername("admin");
    admin.setEmail("admin@clinicindia.com");
    admin.setPassword(passwordEncoder.encode("admin123"));
    admin.setFirstName("Amit");
    admin.setLastName("Sharma");
    // roles removed
    admin.setPhoneNumber("+91-9876543210");
    userRepository.save(admin);

    // Create Doctor Users (Indian details)
    User doctor1 = new User();
    doctor1.setUsername("dr.patel");
    doctor1.setEmail("dr.patel@clinicindia.com");
    doctor1.setPassword(passwordEncoder.encode("doctor123"));
    doctor1.setFirstName("Sneha");
    doctor1.setLastName("Patel");
    // roles removed
    doctor1.setPhoneNumber("+91-9811122233");
    userRepository.save(doctor1);

    User doctor2 = new User();
    doctor2.setUsername("dr.kumar");
    doctor2.setEmail("dr.kumar@clinicindia.com");
    doctor2.setPassword(passwordEncoder.encode("doctor123"));
    doctor2.setFirstName("Rohit");
    doctor2.setLastName("Kumar");
    // roles removed
    doctor2.setPhoneNumber("+91-9822233344");
    userRepository.save(doctor2);

    // Create Patient Users (Indian details)
    User patient1 = new User();
    patient1.setUsername("priya.singh");
    patient1.setEmail("priya.singh@email.com");
    patient1.setPassword(passwordEncoder.encode("patient123"));
    patient1.setFirstName("Priya");
    patient1.setLastName("Singh");
    // roles removed
    patient1.setPhoneNumber("+91-9833344455");
    userRepository.save(patient1);

    User patient2 = new User();
    patient2.setUsername("arjun.verma");
    patient2.setEmail("arjun.verma@email.com");
    patient2.setPassword(passwordEncoder.encode("patient123"));
    patient2.setFirstName("Arjun");
    patient2.setLastName("Verma");
    // roles removed
    patient2.setPhoneNumber("+91-9844455566");
    userRepository.save(patient2);

    // Create Pharmacy User (Indian details)
    User pharmacist = new User();
    pharmacist.setUsername("pharmacy.siddhi");
    pharmacist.setEmail("pharmacy@siddhipharma.com");
    pharmacist.setPassword(passwordEncoder.encode("pharmacy123"));
    pharmacist.setFirstName("Siddhi");
    pharmacist.setLastName("Pharma");
    // roles removed
    pharmacist.setPhoneNumber("+91-9855566677");
    userRepository.save(pharmacist);

        // Create Doctor Profiles
        Doctor drSmith = new Doctor();
    drSmith.setUser(doctor1);
    drSmith.setSpecialization("Cardiology");
    drSmith.setLicenseNumber("IN-MD-1001");
    drSmith.setExperience("18 years");
    drSmith.setEducation("All India Institute of Medical Sciences, Delhi");
    drSmith.setBio("Renowned Indian cardiologist with expertise in heart failure and interventional cardiology.");
    drSmith.setImageUrl("/images/doctors/dr-patel.jpg");
    doctorRepository.save(drSmith);

    Doctor drJohnson = new Doctor();
    drJohnson.setUser(doctor2);
    drJohnson.setSpecialization("Pediatrics");
    drJohnson.setLicenseNumber("IN-MD-1002");
    drJohnson.setExperience("14 years");
    drJohnson.setEducation("King George's Medical University, Lucknow");
    drJohnson.setBio("Experienced pediatrician from India, passionate about child health and immunization.");
    drJohnson.setImageUrl("/images/doctors/dr-kumar.jpg");
    doctorRepository.save(drJohnson);

        // Create Pharmacy
        Pharmacy mainPharmacy = new Pharmacy();
    mainPharmacy.setUser(pharmacist);
    mainPharmacy.setPharmacyName("Siddhi Pharma");
    mainPharmacy.setLicenseNumber("IN-PH-2001");
    mainPharmacy.setAddress("12 MG Road, Mumbai, Maharashtra 400001");
    mainPharmacy.setPhoneNumber("+91-9855566677");
    mainPharmacy.setEmail("pharmacy@siddhipharma.com");
    mainPharmacy.setDescription("Trusted Indian pharmacy providing quality medicines and healthcare products.");
    mainPharmacy.setImageUrl("/images/pharmacy/siddhi-pharma.jpg");
    pharmacyRepository.save(mainPharmacy);

        // Create Medicines
        Medicine medicine1 = new Medicine();
    medicine1.setName("Crocin 500mg");
    medicine1.setDescription("Pain reliever and fever reducer");
    medicine1.setManufacturer("GlaxoSmithKline India");
    medicine1.setPrice(new BigDecimal("12.00"));
    medicine1.setDosage("500mg tablet");
    medicine1.setCategory("Pain Relief");
    medicine1.setPrescriptionRequired(false);
    medicine1.setStockQuantity(120);
    medicine1.setImageUrl("/images/medicines/crocin.jpg");
    medicine1.setPharmacy(mainPharmacy);
    medicineRepository.save(medicine1);

        Medicine medicine2 = new Medicine();
    medicine2.setName("Augmentin 625mg");
    medicine2.setDescription("Antibiotic for bacterial infections");
    medicine2.setManufacturer("Sun Pharma");
    medicine2.setPrice(new BigDecimal("28.00"));
    medicine2.setDosage("625mg tablet");
    medicine2.setCategory("Antibiotics");
    medicine2.setPrescriptionRequired(true);
    medicine2.setStockQuantity(60);
    medicine2.setImageUrl("/images/medicines/augmentin.jpg");
    medicine2.setPharmacy(mainPharmacy);
    medicineRepository.save(medicine2);

        Medicine medicine3 = new Medicine();
    medicine3.setName("Shelcal 500mg");
    medicine3.setDescription("Calcium supplement for bone health");
    medicine3.setManufacturer("Torrent Pharmaceuticals");
    medicine3.setPrice(new BigDecimal("18.00"));
    medicine3.setDosage("500mg tablet");
    medicine3.setCategory("Vitamins & Supplements");
    medicine3.setPrescriptionRequired(false);
    medicine3.setStockQuantity(80);
    medicine3.setImageUrl("/images/medicines/shelcal.jpg");
    medicine3.setPharmacy(mainPharmacy);
    medicineRepository.save(medicine3);

        // Create Appointments
        Appointment appointment1 = new Appointment();
    appointment1.setUser(patient1);
    appointment1.setDoctor(drSmith);
    appointment1.setAppointmentDateTime(LocalDateTime.now().plusDays(7).withHour(10).withMinute(0));
    appointment1.setStatus(AppointmentStatus.SCHEDULED);
    appointment1.setReason("Routine heart checkup");
    appointment1.setNotes("Patient requested morning appointment in Mumbai");
    appointmentRepository.save(appointment1);

    Appointment appointment2 = new Appointment();
    appointment2.setUser(patient2);
    appointment2.setDoctor(drJohnson);
    appointment2.setAppointmentDateTime(LocalDateTime.now().plusDays(5).withHour(14).withMinute(30));
    appointment2.setStatus(AppointmentStatus.CONFIRMED);
    appointment2.setReason("Child vaccination");
    appointment2.setNotes("Bring vaccination record to Lucknow clinic");
    appointmentRepository.save(appointment2);

        System.out.println("✅ Sample data initialized successfully!");
        System.out.println("👥 Users created: " + userRepository.count());
        System.out.println("👨‍⚕️ Doctors created: " + doctorRepository.count());
        System.out.println("💊 Pharmacy created: " + pharmacyRepository.count());
        System.out.println("💊 Medicines created: " + medicineRepository.count());
        System.out.println("📅 Appointments created: " + appointmentRepository.count());
        System.out.println("\n🔑 Default Login Credentials:");
    System.out.println("Admin: admin / admin123");
    System.out.println("Doctor: dr.patel / doctor123");
    System.out.println("Patient: priya.singh / patient123");
    System.out.println("Pharmacy: pharmacy.siddhi / pharmacy123");
    }
}
