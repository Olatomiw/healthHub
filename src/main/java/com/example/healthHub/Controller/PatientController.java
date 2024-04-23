package com.example.healthHub.Controller;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.Patient.PatientReportDto;
import com.example.healthHub.Model.Patient;
import com.example.healthHub.Model.PatientsReport;
import com.example.healthHub.Repository.PatientReportRepository;
import com.example.healthHub.Repository.PatientRepository;
import com.example.healthHub.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;


    @Autowired
    private PatientReportRepository patientReportRepository;

//    Patient Controller Class
    @PostMapping("/new_patient")
    private ResponseEntity<?> createPatientProfile(@Valid @RequestBody PatientDto patientDto){
        return patientService.newPatient(patientDto);
    }



    @PostMapping("/patientRecord/{id}")
    private ResponseEntity<?> patientRecord(@PathVariable Long id, @Valid @RequestBody PatientReportDto patientReportDto){
        return patientService.patientRecord(patientReportDto, id);

    }

    @PostMapping("/doctorsReport/{id}")
    private ResponseEntity<?> doctorsReport(@PathVariable Long id,@RequestBody PatientReportDto patientReportDto){
        return patientService.doctorReport(id,patientReportDto);
    }
    @GetMapping("/getPatient/{id}")
    private ResponseEntity<?> getPatient(@Valid @PathVariable Long id){
        Optional<Patient> byId = patientRepository.findById(id);
        try {
            if (byId.isEmpty()){
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            Patient patient = byId.get();
            List<PatientsReport> reportList = patient.getReportList();
            return new ResponseEntity<>(byId,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getActive")
    private List<PatientsReport> getActive(){
        return patientReportRepository.findAllByActive(true);
    }


    @GetMapping("patientActiveReport/{id}")
    private PatientsReport getMyReport(@PathVariable Long id){
        Optional<PatientsReport>activeReportByPatientId = patientReportRepository.findActiveReportByPatientId(id);
        PatientsReport report = activeReportByPatientId.get();
        return report;
    }

    @PostMapping("setToFalse/{id}")
    private ResponseEntity<?> setReportToFalse(@PathVariable Long id){
        return patientService.setActiveSessionToFalse(id);
    }
}
