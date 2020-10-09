package com.example.dietapp;

public class BmiValue {

    private String BMI;
    private String Status ;

    public BmiValue() {

    }

    public BmiValue(String BMI, String status) {
        this.BMI = BMI;
        Status = status;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
