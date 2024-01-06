package com.example.healthHub.Controller;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    private PatientService patientService;
//    Patient Controller Class
    @PostMapping("/new_patient")
    private ResponseEntity<?> createPatientProfile(@Valid @RequestBody PatientDto patientDto, HttpServletRequest request){
        return patientService.newPatient(patientDto, request);
    }

}
