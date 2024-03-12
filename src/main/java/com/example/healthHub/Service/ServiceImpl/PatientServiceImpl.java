package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.response.ApiResponse;
import com.example.healthHub.Model.Patient;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Repository.PatientRepository;
import com.example.healthHub.Service.CookieAuthenticationService;
import com.example.healthHub.Service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service

public class PatientServiceImpl implements PatientService {
    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;
    @Autowired
    private PatientRepository patientRepository;


    @Override
//    Creating new Patient
    public ResponseEntity<?> newPatient(PatientDto patientDto, HttpServletRequest request) {
        Profile loggedInStaff = cookieAuthenticationService.getLoggedInStaff(request);
        String name = loggedInStaff.getFirstName();
        String role = loggedInStaff.getRole();
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Patient patient = new Patient();
        try {
            if(!role.equalsIgnoreCase("Doctor")||!role.equalsIgnoreCase("nurse")){
                apiResponse.setMessage("Unauthorized");
                return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            patient.setFullName(patientDto.getName());
            patient.setAge(patientDto.getAge());
            patient.setAddress(patientDto.getAddress());
            patient.setCreatedAt(Instant.now().plus(1, ChronoUnit.HOURS));
            patient.setCreatedBy(name);
            patientRepository.save(patient);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
