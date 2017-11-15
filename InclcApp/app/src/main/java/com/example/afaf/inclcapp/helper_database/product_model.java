package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 24/04/17.
 */

public class product_model {

    private int id;
    private String productName;
    private String sessionno;
    private String netunitprice;
    private String unitno;
    private String productID;
    private String warehouseRuleType;
    private String service;
    private String serviceProductId;
    private String productHisId;


    public product_model() {
    }


    public product_model(int id, String productName, String sessionno, String netunitprice, String unitno, String productID, String warehouseRuleType, String service, String serviceProductId, String productHisId) {
        this.id = id;
        this.productName = productName;
        this.sessionno = sessionno;
        this.netunitprice = netunitprice;
        this.unitno = unitno;
        this.productID = productID;
        this.warehouseRuleType = warehouseRuleType;
        this.service = service;
        this.serviceProductId = serviceProductId;
        this.productHisId = productHisId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSessionno() {
        return sessionno;
    }

    public void setSessionno(String sessionno) {
        this.sessionno = sessionno;
    }

    public String getNetunitprice() {
        return netunitprice;
    }

    public void setNetunitprice(String netunitprice) {
        this.netunitprice = netunitprice;
    }

    public String getUnitno() {
        return unitno;
    }

    public void setUnitno(String unitno) {
        this.unitno = unitno;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getWarehouseRuleType() {
        return warehouseRuleType;
    }

    public void setWarehouseRuleType(String warehouseRuleType) {
        this.warehouseRuleType = warehouseRuleType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceProductId() {
        return serviceProductId;
    }

    public void setServiceProductId(String serviceProductId) {
        this.serviceProductId = serviceProductId;
    }

    public void setProductHistoryID(String productHisId) {
        this.productHisId = productHisId;
    }

    public String getProductHistoryID() {
        return productHisId;
    }

    @Override
    public String toString() {
        return "product_model{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", sessionno='" + sessionno + '\'' +
                ", netunitprice='" + netunitprice + '\'' +
                ", unitno='" + unitno + '\'' +
                ", productID='" + productID + '\'' +
                ", warehouseRuleType='" + warehouseRuleType + '\'' +
                ", service='" + service + '\'' +
                ", serviceProductId='" + serviceProductId + '\'' +
                ", productHisId='" + productHisId + '\'' +
                '}';
    }
}
