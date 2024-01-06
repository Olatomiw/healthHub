package com.example.healthHub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
    private String doctorsReport;
    private String prescribedDrugs;
    private String doctorInCharge;
    private String nurseInCharge;
    private String labAttendant;
}
