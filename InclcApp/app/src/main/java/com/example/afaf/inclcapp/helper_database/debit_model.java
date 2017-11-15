package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 22/05/17.
 */

public class debit_model {
    private int id;
    private String dDate;
    private String dAmount;
    private String dAppointmentID;
    private String dAppointmentName;
    private String dProductID;
    private String dProductName;
    private String dNetPrice;
    private String dUnitNo;
    private String dId;

    public debit_model() {
    }

    public debit_model(int id, String dDate, String dAmount, String dAppointmentID, String dAppointmentName, String dProductID, String dProductName, String dNetPrice, String dUnitNo, String dId) {
        this.id = id;
        this.dDate = dDate;
        this.dAmount = dAmount;
        this.dAppointmentID = dAppointmentID;
        this.dAppointmentName = dAppointmentName;
        this.dProductID = dProductID;
        this.dProductName = dProductName;
        this.dNetPrice = dNetPrice;
        this.dUnitNo = dUnitNo;
        this.dId = dId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate;
    }

    public String getdAmount() {
        return dAmount;
    }

    public void setdAmount(String dAmount) {
        this.dAmount = dAmount;
    }

    public String getdAppointmentID() {
        return dAppointmentID;
    }

    public void setdAppointmentID(String dAppointmentID) {
        this.dAppointmentID = dAppointmentID;
    }

    public String getdAppointmentName() {
        return dAppointmentName;
    }

    public void setdAppointmentName(String dAppointmentName) {
        this.dAppointmentName = dAppointmentName;
    }

    public String getdProductID() {
        return dProductID;
    }

    public void setdProductID(String dProductID) {
        this.dProductID = dProductID;
    }

    public String getdProductName() {
        return dProductName;
    }

    public void setdProductName(String dProductName) {
        this.dProductName = dProductName;
    }

    public String getdNetPrice() {
        return dNetPrice;
    }

    public void setdNetPrice(String dNetPrice) {
        this.dNetPrice = dNetPrice;
    }

    public String getdUnitNo() {
        return dUnitNo;
    }

    public void setdUnitNo(String dUnitNo) {
        this.dUnitNo = dUnitNo;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    @Override
    public String toString() {
        return "debit_model{" +
                "id=" + id +
                ", dDate='" + dDate + '\'' +
                ", dAmount='" + dAmount + '\'' +
                ", dAppointmentID='" + dAppointmentID + '\'' +
                ", dAppointmentName='" + dAppointmentName + '\'' +
                ", dProductID='" + dProductID + '\'' +
                ", dProductName='" + dProductName + '\'' +
                ", dNetPrice='" + dNetPrice + '\'' +
                ", dUnitNo='" + dUnitNo + '\'' +
                ", dId='" + dId + '\'' +
                '}';
    }
}
