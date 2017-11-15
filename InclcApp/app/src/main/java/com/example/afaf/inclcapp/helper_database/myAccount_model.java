package com.example.afaf.inclcapp.helper_database;

/**
 * Created by enterprise on 21/05/17.
 */

public class myAccount_model {

    private int id;
    private String balance;
    private String totalDebit;
    private String totalCredit;

    public myAccount_model() {
    }

    public myAccount_model(int id, String balance, String totalDebit, String totalCredit) {
        this.id = id;
        this.balance = balance;
        this.totalDebit = totalDebit;
        this.totalCredit = totalCredit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(String totalDebit) {
        this.totalDebit = totalDebit;
    }

    public String getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }


    @Override
    public String toString() {
        return "myAccount_model{" +
                "id=" + id +
                ", balance='" + balance + '\'' +
                ", totalDebit='" + totalDebit + '\'' +
                ", totalCredit='" + totalCredit + '\'' +
                '}';
    }
}
