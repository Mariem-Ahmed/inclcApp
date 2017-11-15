package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 19/04/17.
 */

public class clinic_model {
    private int id;
    private String key;
    private String name;
    private String clinicId;

    public clinic_model() {
    }

    public clinic_model(int id, String key, String name, String clinicId) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.clinicId = clinicId;
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

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }


    @Override
    public String toString() {
        return "clinic_model{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", clinicId='" + clinicId + '\'' +
                '}';
    }
}
