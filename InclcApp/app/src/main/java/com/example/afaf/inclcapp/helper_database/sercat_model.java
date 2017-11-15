package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 26/04/17.
 */

public class sercat_model {

    private int id;
    private String key;
    private String name;
    private String sercatId;

    public sercat_model() {
    }

    public sercat_model(int id, String key, String name, String sercatId) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.sercatId = sercatId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSercatId() {
        return sercatId;
    }

    public void setSercatId(String sercatId) {
        this.sercatId = sercatId;
    }

    @Override
    public String toString() {
        return "sercat_model{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", sercatId='" + sercatId + '\'' +
                '}';
    }
}
