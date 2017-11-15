package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 11/04/17.
 */

public class appointment_Model {
    private int id;
    private String date;
    private String doctor;
    private String clinic;
    private String startTime;
    private String endTime;
    private String numOfCustomer;
    private String numOfServedCustomer;
    private String appointmentStatus;
    private String appointmentId;


    public appointment_Model() {
    }

    public appointment_Model(int id, String date, String doctor, String clinic, String startTime, String endTime, String numOfCustomer, String numOfServedCustomer, String appointmentStatus, String appointmentId) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.clinic = clinic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numOfCustomer = numOfCustomer;
        this.numOfServedCustomer = numOfServedCustomer;
        this.appointmentStatus = appointmentStatus;
        this.appointmentId = appointmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNumOfCustomer() {
        return numOfCustomer;
    }

    public void setNumOfCustomer(String numOfCustomer) {
        this.numOfCustomer = numOfCustomer;
    }

    public String getNumOfServedCustomer() {
        return numOfServedCustomer;
    }

    public void setNumOfServedCustomer(String numOfServedCustomer) {
        this.numOfServedCustomer = numOfServedCustomer;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }


    @Override
    public String toString() {
        return "appointment_Model{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", doctor='" + doctor + '\'' +
                ", clinic='" + clinic + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", numOfCustomer='" + numOfCustomer + '\'' +
                ", numOfServedCustomer='" + numOfServedCustomer + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                '}';
    }
}
