package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 23/04/17.
 */

public class patient_model {
    private int id;
    private String patientName;
    private String serviceName;
    private String sqe;
    private String patientId;
    private String serviceID;
    private String custID;
    private String remain;

    public patient_model() {
    }

    public patient_model(int id, String patientName, String serviceName, String sqe, String patientId, String serviceID, String custID, String remain) {
        this.id = id;
        this.patientName = patientName;
        this.serviceName = serviceName;
        this.sqe = sqe;
        this.patientId = patientId;
        this.serviceID = serviceID;
        this.custID = custID;
        this.remain = remain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSqe() {
        return sqe;
    }

    public void setSqe(String sqe) {
        this.sqe = sqe;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "patient_model{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", sqe='" + sqe + '\'' +
                ", patientId='" + patientId + '\'' +
                ", serviceID='" + serviceID + '\'' +
                ", custID='" + custID + '\'' +
                ", remain='" + remain + '\'' +
                '}';
    }
}
