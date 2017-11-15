package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 25/04/17.
 */

public class services_model {

    private int id;
    private String serviceId;
    private String serviceName;
    private String addServiceId;
    private String cost;
    private String price;
    private String serviceCategory;
    private String serviceCategoryId;
    private String unitnum;

    public services_model() {
    }


    public services_model(int id, String serviceId, String serviceName, String addServiceId, String cost, String price, String serviceCategory, String serviceCategoryId, String unitnum) {
        this.id = id;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.addServiceId = addServiceId;
        this.cost = cost;
        this.price = price;
        this.serviceCategory = serviceCategory;
        this.serviceCategoryId = serviceCategoryId;
        this.unitnum = unitnum;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddServiceId() {
        return addServiceId;
    }

    public void setAddServiceId(String addServiceId) {
        this.addServiceId = addServiceId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(String serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }

    @Override
    public String toString() {
        return "services_model{" +
                "id=" + id +
                ", serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", addServiceId='" + addServiceId + '\'' +
                ", cost='" + cost + '\'' +
                ", price='" + price + '\'' +
                ", serviceCategory='" + serviceCategory + '\'' +
                ", serviceCategoryId='" + serviceCategoryId + '\'' +
                ", unitnum='" + unitnum + '\'' +
                '}';
    }
}
