package com.example.jpa.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table
@XmlRootElement
public class Account implements Serializable {
    
//declaring variables & setting ID as the primary key auto increment in database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountNum;
    private double balance;
    private int sort_code;
    //private Customer customer;

    public Account() {
        
    }

    public Account(double balance, int sort_code, Customer customer) {
        this.balance = balance;
        this.sort_code = sort_code;
        this.customer = customer;
    }
    
//GETTERS AND SETTERS
    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getSort_code() {
        return sort_code;
    }

    public void setSort_code(int sort_code) {
        this.sort_code = sort_code;
    }
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transfer;

    public void setTransactions(List<Transaction> transferlist) {
        this.transfer = transferlist;
    }

    @XmlElementWrapper(name = "transactions")
    @XmlElementRef()
    public List<Transaction> getTransfers() {
        return transfer;
    }
    
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
    
    @Override
    public String toString() {
        return "Transaction{" + "id=" + accountNum + ", customer=" + customer.getName() + '}';
    }
}
