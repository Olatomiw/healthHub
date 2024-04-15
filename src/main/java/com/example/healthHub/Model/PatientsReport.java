package com.example.healthHub.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Entity
@Getter
@Setter
public class PatientsReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private String height;
        private String weight;
        private String bloodPressure;
    private String labReport;
    private List<String> doctorsReport;
    private String prescribedDrugs;
    private String doctorInCharge;
    private String nurseInCharge;
    private String labAttendant;
    private Boolean active = false;

//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;
    public String getHeight() {
        return height;
    }


    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getLabReport() {
        return labReport;
    }

    public void setLabReport(String labReport) {
        this.labReport = labReport;
    }

    public List<String> getDoctorsReport() {
        return doctorsReport;
    }

    public void setDoctorsReport(List<String> doctorsReport) {
        this.doctorsReport = doctorsReport;
    }

    public String getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(String prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }

    public String getDoctorInCharge() {
        return doctorInCharge;
    }

    public void setDoctorInCharge(String doctorInCharge) {
        this.doctorInCharge = doctorInCharge;
    }

    public String getNurseInCharge() {
        return nurseInCharge;
    }

    public void setNurseInCharge(String nurseInCharge) {
        this.nurseInCharge = nurseInCharge;
    }

    public String getLabAttendant() {
        return labAttendant;
    }

    public void setLabAttendant(String labAttendant) {
        this.labAttendant = labAttendant;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
