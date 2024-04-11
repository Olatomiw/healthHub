package com.example.healthHub.Repository;

import com.example.healthHub.Model.Patient;
import com.example.healthHub.Model.PatientsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientReportRepository extends JpaRepository<PatientsReport, Long> {

    List<PatientsReport> findAllByActive(Boolean active);
//    Optional<List<PatientsReport>>findByActiveAndPatient(Boolean active, Patient patient);
//    @Query("SELECT pr FROM PatientsReport pr JOIN pr.patient p WHERE p.id = :patientId")
//    List<PatientsReport>findByPatientId(Long patientId);
//    }
    @Query(value = "SELECT * FROM patients_report WHERE patient_id = ?1 AND active = true", nativeQuery = true)
    Optional<PatientsReport> findActiveReportByPatientId(@Param("patientId") Long patientId);
}
