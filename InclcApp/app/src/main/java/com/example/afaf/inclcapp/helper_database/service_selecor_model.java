package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 14/05/17.
 */

public class service_selecor_model {
    private int id;
    private String serID;
    private String servicename;

    public service_selecor_model() {
    }


    public service_selecor_model(int id, String serID, String servicename) {
        this.id = id;
        this.serID = serID;
        this.servicename = servicename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerID() {
        return serID;
    }

    public void setSerID(String serID) {
        this.serID = serID;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    @Override
    public String toString() {
        return "service_selecor_model{" +
                "id=" + id +
                ", serID='" + serID + '\'' +
                ", servicename='" + servicename + '\'' +
                '}';
    }
}
