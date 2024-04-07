package com.example.healthHub.Repository;

import com.example.healthHub.Model.PatientsReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientReportRepository extends JpaRepository<PatientsReport, Long> {

    List<PatientsReport> findAllByActive(Boolean active);
}
