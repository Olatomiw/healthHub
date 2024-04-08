package com.example.healthHub.Service;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.Patient.PatientReportDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    ResponseEntity<?> newPatient(PatientDto patientDto);
    ResponseEntity<?>patientRecord(PatientReportDto patientReportDto, Long id);

    ResponseEntity<?>doctorReport(Long id, PatientReportDto patientReportDto);

    ResponseEntity<?> activeSessions();
}
