package com.example.jpa.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;
import java.text.*;

@Entity
@Table
@XmlRootElement
public class Transaction implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
    private double amount, post_balance;
    Date dNow = new Date();

    public Transaction() {
    }

    public Transaction(String type, double amount,double post_balance, Date dNow, Customer customer, Account account) {
        this.type = type;
        this.amount = amount;
        this.post_balance = post_balance;
        this.customer = customer;
        this.account = account;
        this.dNow = dNow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPost_balance() {
        return post_balance;
    }

    public void setPost_balance(double post_balance) {
        this.post_balance = post_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public Date getdNow() {
        return dNow;
    }

    public void setdNow(Date dNow) {
        this.dNow = dNow;
    }
    //MANY TRANSACTIONS TO ONE CUSTOMER
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

   //MANY TRANSCTIONS TO ONE ACCOUNT
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="account_fk")
    private Account account;

    @XmlTransient
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", user=" + account.getBalance() + '}';
    }
    
}
