package com.example.healthHub.Repository;

import com.example.healthHub.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    Optional<Patient>findByPatientId(String patientId);
    Optional<Patient>findByPhoneNumber(Long number);

//    Optional<Patient>findByIdAndActive(Long id, Boolean active);

}
