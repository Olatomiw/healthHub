package com.example.healthHub.Service;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.Patient.PatientReportDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    ResponseEntity<?> newPatient(PatientDto patientDto);
    ResponseEntity<?>patientRecord(String patientId, PatientReportDto patientReportDto);

    ResponseEntity<?> activeSessions();
}
