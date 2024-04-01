package com.example.healthHub.Controller;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Service.PatientService;
import jakarta.persistence.Access;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PatientController {
    @Autowired
    private PatientService patientService;
//    Patient Controller Class
    @PostMapping("/new_patient")
    private ResponseEntity<?> createPatientProfile(@Valid @RequestBody PatientDto patientDto){
        return patientService.newPatient(patientDto);
    }

    @GetMapping("/patient")
    private ResponseEntity<?> create(){
        return patientService.ourPatient();
    }

}
