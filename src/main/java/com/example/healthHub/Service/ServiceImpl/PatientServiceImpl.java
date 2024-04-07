package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Config.UserRole;
import com.example.healthHub.Dto.Patient.PatientDto;
import com.example.healthHub.Dto.Patient.PatientReportDto;
import com.example.healthHub.Dto.response.ApiResponse;
import com.example.healthHub.Model.Patient;
import com.example.healthHub.Model.PatientsReport;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Repository.PatientReportRepository;
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

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service

public class PatientServiceImpl implements PatientService {
    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CurrentLoggedInUserService currentLoggedInUserService;

    @Autowired
    private PatientReportRepository patientReportRepository;


    @Override
//    Creating new Patient
    public ResponseEntity<?> newPatient(PatientDto patientDto) {
        ResponseEntity<ProfileInstance> currentLoggedInUser = currentLoggedInUserService.getCurrentLoggedInUser();
        ProfileInstance profileDetails = currentLoggedInUser.getBody();
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Long count = patientRepository.count();
        int currentYear = Year.now().getValue();
        Long idKey= ++count;
        Patient patient = new Patient();
        try {
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setPhoneNumber(patientDto.getPhoneNumber());
            patient.setAge(patientDto.getAge());
            patient.setAddress(patientDto.getAddress());
            patient.setCreatedAt(Instant.now().plus(1, ChronoUnit.HOURS));
            patient.setBloodGroup(patientDto.getBloodGroup());
            patient.setGenotype(patientDto.getGenotype());
            patient.setDateOfBirth(patientDto.getDate());
            patient.setCreatedBy(profileDetails.getStaffId());
            patient.setPatientId(String.format("PH/%04d/%d", idKey,currentYear));

            Optional<Patient>optionalPatient=patientRepository.findByPhoneNumber(patientDto.getPhoneNumber());
            if (optionalPatient.isPresent()){
                return new ResponseEntity<>("Existing Profile", HttpStatus.BAD_REQUEST);
            }
            patientRepository.save(patient);
            return new ResponseEntity<>("successful", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> patientRecord(String patientId, PatientReportDto patientReportDto) {
        ResponseEntity<ProfileInstance> currentLoggedInUser = currentLoggedInUserService.getCurrentLoggedInUser();
        ProfileInstance loggedInUser = currentLoggedInUser.getBody();
        Optional<Patient> byPatientId = patientRepository.findByPatientId(patientId);
        PatientsReport patientsReport = new PatientsReport();
        try {
            if (byPatientId.isEmpty()){
                return new ResponseEntity<>("NotFound", HttpStatus.NOT_FOUND);
            }
            Patient patient = byPatientId.get();
            List<PatientsReport> reportList = patient.getReportList();
            if (loggedInUser.getRole() == UserRole.ROLE_NURSE){
                patientsReport.setBloodPressure(patientReportDto.getBloodPressure());
                patientsReport.setHeight(patientReportDto.getHeight());
                patientsReport.setWeight(patientReportDto.getWeight());
                reportList.add(patientsReport);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public ResponseEntity<?> activeSessions() {
        List<PatientsReport> allByActive = patientReportRepository.findAllByActive(true);
        return new ResponseEntity<>("allByActive", HttpStatus.OK);
    }


}
