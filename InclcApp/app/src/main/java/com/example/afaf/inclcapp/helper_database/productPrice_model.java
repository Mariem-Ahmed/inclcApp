package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 11/05/17.
 */

public class productPrice_model {
    private int id;
    private String product;
    private String price;
    private String productpriceId;

    public productPrice_model() {
    }

    public productPrice_model(int id, String product, String price, String productpriceId) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.productpriceId = productpriceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductpriceId() {
        return productpriceId;
    }

    public void setProductpriceId(String productpriceId) {
        this.productpriceId = productpriceId;
    }


    @Override
    public String toString() {
        return "productPrice_model{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", price='" + price + '\'' +
                ", productpriceId='" + productpriceId + '\'' +
                '}';
    }
}
