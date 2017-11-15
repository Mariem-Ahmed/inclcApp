package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 27/04/17.
 */

public class productSelector_model {


    private int id;
    private String prodkey;
    private String prodname;
    private String prodId;


    public productSelector_model() {
    }

    public productSelector_model(int id, String prodkey, String prodname, String prodId) {
        this.id = id;
        this.prodkey = prodkey;
        this.prodname = prodname;
        this.prodId = prodId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdkey() {
        return prodkey;
    }

    public void setProdkey(String prodkey) {
        this.prodkey = prodkey;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }


    @Override
    public String toString() {
        return "productSelector_model{" +
                "id=" + id +
                ", prodkey='" + prodkey + '\'' +
                ", prodname='" + prodname + '\'' +
                ", prodId='" + prodId + '\'' +
                '}';
    }
}
