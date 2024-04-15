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
import java.util.ArrayList;
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

// Getting Patient Active Record
    public PatientsReport getPatientReport(Long id){
        Optional<PatientsReport> activeReportByPatientId =
                patientReportRepository.findActiveReportByPatientId(id);
        if (activeReportByPatientId.isPresent()){
            PatientsReport report = activeReportByPatientId.get();
            return report;
        }
        return null;
    }





    @Override
    public ResponseEntity<?> patientRecord( PatientReportDto patientReportDto ,Long id) {
        ResponseEntity<ProfileInstance> currentLoggedInUser = currentLoggedInUserService.getCurrentLoggedInUser();
        ProfileInstance loggedInUser = currentLoggedInUser.getBody();
        Optional<Patient> byPatientId = patientRepository.findById(id);
        try {
            if (byPatientId.isEmpty()){
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            Patient patient = byPatientId.get();
            List<PatientsReport> reportList = patient.getReportList();
            if (loggedInUser.getRole() == UserRole.ROLE_NURSE){
                PatientsReport patientsReport = new PatientsReport();
                patientsReport.setBloodPressure(patientReportDto.getBloodPressure());
                patientsReport.setHeight(patientReportDto.getHeight());
                patientsReport.setWeight(patientReportDto.getWeight());
                patientsReport.setActive(true);
                patientsReport.setNurseInCharge(loggedInUser.getStaffId());
                reportList.add(patientsReport);
                if (getPatientReport(id)!=null){
                    return new ResponseEntity<>("Patient has an existing session that's yet to be closed",
                            HttpStatus.CONFLICT);
                }
                else if (getPatientReport(id) == null){
                    patientRepository.save(patient);
                    return new ResponseEntity<>(patient, HttpStatus.OK);
                }
            }
             else {
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<?> doctorReport(Long id, PatientReportDto patientReportDto) {
        ResponseEntity<ProfileInstance> currentLoggedInUser =
                currentLoggedInUserService.getCurrentLoggedInUser();
        ProfileInstance loggedInUser = currentLoggedInUser.getBody();
        Optional<Patient> byPatientId = patientRepository.findById(id);
        Patient patient = byPatientId.get();
        PatientsReport existingReport = existingRecord(patient);
        try {
            if (byPatientId.isEmpty()){
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            if (getPatientReport(id) != null && getPatientReport(id).getActive().equals(true)){
                PatientsReport patientReport = getPatientReport(id);
                List<String>myReport = new ArrayList<>();
                myReport.add(patientReportDto.getDoctorsReport());
                patientReport.setDoctorsReport(myReport);
                patientReport.setDoctorInCharge(loggedInUser.getStaffId());
                patientReport.setPrescribedDrugs(patientReportDto.getPrescribedDrugs());
                patientRepository.save(patient);
                return new ResponseEntity<>(patient, HttpStatus.OK);
            } else if (getPatientReport(id) == null) {
                PatientsReport newReport = new PatientsReport();
                List<PatientsReport> reportList = patient.getReportList();
                newReport.setActive(Boolean.TRUE);
                newReport.getDoctorsReport().add(patientReportDto.getDoctorsReport());
                newReport.setDoctorInCharge(loggedInUser.getStaffId());
                newReport.setPrescribedDrugs(patientReportDto.getPrescribedDrugs());
                reportList.add(newReport);
                patientRepository.save(patient);
                return new ResponseEntity<>(patient, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> activeSessions() {
        List<PatientsReport> allByActive = patientReportRepository.findAllByActive(true);
        return new ResponseEntity<>(allByActive, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> setActiveSessionToFalse(Long id) {
        Optional<Patient> byId = patientRepository.findById(id);
        try {
            if (byId.isEmpty()){
                return new ResponseEntity<>("Patient Not Found", HttpStatus.NOT_FOUND);
            }
            Patient foundPatient = byId.get();
            Long patientId = foundPatient.getId();
            Optional<PatientsReport> activeReportByPatientId =
                    patientReportRepository.findActiveReportByPatientId(patientId);
            if (activeReportByPatientId.isEmpty()){
                return new ResponseEntity<>("No Active session found", HttpStatus.NOT_FOUND);
            } else if (activeReportByPatientId.isPresent()) {
                PatientsReport report = activeReportByPatientId.get();
                report.setActive(false);
                patientRepository.save(foundPatient);
                return new ResponseEntity<>(foundPatient, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //    Method
    public PatientsReport existingRecord(Patient patient){
        for (PatientsReport report : patient.getReportList()){
            if (report.getActive().equals(true)){
                return report;
            }
        }
        return null;
    }

//

}
