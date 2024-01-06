package com.example.healthHub.Repository;

import com.example.healthHub.Model.PatientsReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientReportRepository extends JpaRepository<PatientsReport, Long> {

}
