package com.example.healthHub.Dto.Patient;

public class PatientReportDto {
    private String height;
    private String weight;
    private String bloodPressure;
    private String labReport;
    private String doctorsReport;
    private String prescribedDrugs;

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

    public String getDoctorsReport() {
        return doctorsReport;
    }

    public void setDoctorsReport(String doctorsReport) {
        this.doctorsReport = doctorsReport;
    }

    public String getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(String prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }
}
