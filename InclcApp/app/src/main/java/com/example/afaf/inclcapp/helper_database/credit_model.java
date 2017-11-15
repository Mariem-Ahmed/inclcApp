package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 22/05/17.
 */

public class credit_model {
    private int id;
    private String cDate;
    private String cAmount;
    private String cAppointmentID;
    private String cAppointmentName;
    private String cProductID;
    private String cProductName;
    private String cNetPrice;
    private String cUnitNo;
    private String cId;


    public credit_model() {
    }

    public credit_model(int id, String cDate, String cAmount, String cAppointmentID, String cAppointmentName, String cProductID, String cProductName, String cNetPrice, String cUnitNo, String cId) {
        this.id = id;
        this.cDate = cDate;
        this.cAmount = cAmount;
        this.cAppointmentID = cAppointmentID;
        this.cAppointmentName = cAppointmentName;
        this.cProductID = cProductID;
        this.cProductName = cProductName;
        this.cNetPrice = cNetPrice;
        this.cUnitNo = cUnitNo;
        this.cId = cId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getcAmount() {
        return cAmount;
    }

    public void setcAmount(String cAmount) {
        this.cAmount = cAmount;
    }

    public String getcAppointmentID() {
        return cAppointmentID;
    }

    public void setcAppointmentID(String cAppointmentID) {
        this.cAppointmentID = cAppointmentID;
    }

    public String getcAppointmentName() {
        return cAppointmentName;
    }

    public void setcAppointmentName(String cAppointmentName) {
        this.cAppointmentName = cAppointmentName;
    }

    public String getcProductID() {
        return cProductID;
    }

    public void setcProductID(String cProductID) {
        this.cProductID = cProductID;
    }

    public String getcProductName() {
        return cProductName;
    }

    public void setcProductName(String cProductName) {
        this.cProductName = cProductName;
    }

    public String getcNetPrice() {
        return cNetPrice;
    }

    public void setcNetPrice(String cNetPrice) {
        this.cNetPrice = cNetPrice;
    }

    public String getcUnitNo() {
        return cUnitNo;
    }

    public void setcUnitNo(String cUnitNo) {
        this.cUnitNo = cUnitNo;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "credit_model{" +
                "id=" + id +
                ", cDate='" + cDate + '\'' +
                ", cAmount='" + cAmount + '\'' +
                ", cAppointmentID='" + cAppointmentID + '\'' +
                ", cAppointmentName='" + cAppointmentName + '\'' +
                ", cProductID='" + cProductID + '\'' +
                ", cProductName='" + cProductName + '\'' +
                ", cNetPrice='" + cNetPrice + '\'' +
                ", cUnitNo='" + cUnitNo + '\'' +
                ", cId='" + cId + '\'' +
                '}';
    }
}
