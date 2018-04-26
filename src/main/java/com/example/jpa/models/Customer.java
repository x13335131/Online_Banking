package com.example.jpa.models;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Account.class})

public class Customer implements Serializable {

    //declaring variables and setting ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name,email, address;
    private int credentials;
    
    //CONSTRUCTOR
    public Customer() {
      account = new ArrayList<>();
      transaction = new ArrayList<>();
    }
    //CONSTRUCTOR WITH PARAMS
   public Customer(String name, String email, String address, int credentials) {
       transaction = new ArrayList<>();
        account = new ArrayList<>();
        this.name = name;
        this.email = email;
        this.address = address;
        this.credentials = credentials;
    }

    //GETTERS AND SETTERS

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCredentials() {
        return credentials;
    }

    public void setCredentials(int credentials) {
        this.credentials = credentials;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
      public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
   
    //ONE CUSTOMER HAS MANY ACCOUNTS
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Account> account;
    public void setAccounts(List<Account> accountlist) {
        this.account = accountlist;
    }

    @XmlElementWrapper(name = "accounts")
    @XmlElementRef()
    public List<Account> getAccounts() {
        return account;
    }
    
    //ONE CUSTOMER HAS MANY TRANSACTIONS
     @LazyCollection(LazyCollectionOption.FALSE)//False=Eager-Loading
    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL, mappedBy = "customer")
     private List<Transaction> transaction;
    public void setTransactions(List<Transaction> transactionlist) {
        this.transaction = transactionlist;
    }

    @XmlElementWrapper(name = "transactions")
    @XmlElementRef()
    public List<Transaction> getTransactions() {
        return transaction;
    }
    
    
    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", account=" + account + '}';
    }

}
