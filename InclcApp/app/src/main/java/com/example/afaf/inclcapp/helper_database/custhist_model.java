package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 25/04/17.
 */

public class custhist_model {
    private int id;
    private String custhistId;
    private String servicename;
    private String serviceId;
    private String sessionNo;
    private String date;
    private String customerId;
    private String photo;
    private String notes;

    public custhist_model() {
    }

    public custhist_model(int id, String custhistId, String servicename, String serviceId, String sessionNo, String date, String customerId, String photo, String notes) {
        this.id = id;
        this.custhistId = custhistId;
        this.servicename = servicename;
        this.serviceId = serviceId;
        this.sessionNo = sessionNo;
        this.date = date;
        this.customerId = customerId;
        this.photo = photo;
        this.notes = notes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusthistId() {
        return custhistId;
    }

    public void setCusthistId(String custhistId) {
        this.custhistId = custhistId;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSessionNo() {
        return sessionNo;
    }

    public void setSessionNo(String sessionNo) {
        this.sessionNo = sessionNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "custhist_model{" +
                "id=" + id +
                ", custhistId='" + custhistId + '\'' +
                ", servicename='" + servicename + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", sessionNo='" + sessionNo + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", photo='" + photo + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
