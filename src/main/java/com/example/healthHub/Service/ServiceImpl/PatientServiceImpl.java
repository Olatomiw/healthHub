package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.response.ApiResponse;
import com.example.healthHub.Model.Patient;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Repository.PatientRepository;
import com.example.healthHub.Service.CookieAuthenticationService;
import com.example.healthHub.Service.CurrentLoggedInUserService;
import com.example.healthHub.Service.PatientService;
import com.example.healthHub.Util.ProfileInstance;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service

public class PatientServiceImpl implements PatientService {
    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CurrentLoggedInUserService currentLoggedInUserService;


    @Override
//    Creating new Patient
    public ResponseEntity<?> newPatient(PatientDto patientDto) {
        ResponseEntity<ProfileInstance> currentLoggedInUser = currentLoggedInUserService.getCurrentLoggedInUser();
        ProfileInstance profileDetails = currentLoggedInUser.getBody();
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Patient patient = new Patient();
        try {
            patient.setFullName(patientDto.getName());
            patient.setAge(patientDto.getAge());
            patient.setAddress(patientDto.getAddress());
            patient.setCreatedAt(Instant.now().plus(1, ChronoUnit.HOURS));
            patient.setBloodGroup(patientDto.getBloodGroup());
            patient.setGenotype(patientDto.getGenotype());
            patient.setCreatedBy(profileDetails.getStaffId());
            patientRepository.save(patient);
            return new ResponseEntity<>("successful", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> ourPatient() {
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }

}
