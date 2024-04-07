package com.example.healthHub.Controller;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.Patient.PatientReportDto;
import com.example.healthHub.Model.Patient;
import com.example.healthHub.Repository.PatientRepository;
import com.example.healthHub.Service.PatientService;
import jakarta.persistence.Access;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;
//    Patient Controller Class
    @PostMapping("/new_patient")
    private ResponseEntity<?> createPatientProfile(@Valid @RequestBody PatientDto patientDto){
        return patientService.newPatient(patientDto);
    }



    @PostMapping("/patientRecord/{id}")
    private ResponseEntity<?> patientRecord(@PathVariable Long id, @Valid @RequestBody PatientReportDto patientReportDto){
        return patientService.patientRecord(patientReportDto, id);

    }

    @GetMapping("/getPatient/{id}")
    private ResponseEntity<?> getPatient(@Valid @PathVariable Long id){
        Optional<Patient> byId = patientRepository.findById(id);
        try {
            if (byId.isEmpty()){
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(byId,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);
    }


}
