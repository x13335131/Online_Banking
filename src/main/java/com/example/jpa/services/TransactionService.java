/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jpa.services;

import com.example.jpa.models.Account;
import com.example.jpa.models.Transaction;
import com.example.jpa.models.Customer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author louise
 */

public class TransactionService {
    EntityManager entityManager;
    
    
    public TransactionService() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        entityManager = emfactory.createEntityManager();
    }
   
    
    public Response transaction(int accountNum, String type,double amount, int recieverAccount) {  
      Account myAccount = entityManager.find(Account.class, accountNum);
      Account reciever = entityManager.find(Account.class, recieverAccount);
     
      Customer c = entityManager.find(Customer.class, accountNum);
      entityManager.getTransaction().begin();
        double newBal = 0;
        double newBal_r = 0;
        double currAmount;
        double reciever_currAmount;
        Date date = new Date();
        ArrayList<Transaction> list = new ArrayList<>();
        Transaction transaction1 = new Transaction();
      
        try{
        if(type.equalsIgnoreCase("withdrawal")){
            currAmount = myAccount.getBalance();
            newBal = currAmount - amount;
            System.out.println("withdrawing....");
        }else if(type.equalsIgnoreCase("lodgement")){
            currAmount = myAccount.getBalance();
            newBal = currAmount + amount;
            System.out.println("lodging....");
        }else if(type.equalsIgnoreCase("transfer")){
           // List<Transaction> transfer = new ArrayList<>();
            currAmount = myAccount.getBalance();
            newBal = currAmount - amount;
            reciever_currAmount = reciever.getBalance();
            newBal_r= reciever_currAmount+amount;
             reciever.setBalance(newBal_r);
           System.out.println("transfer....");
        }
        }catch(Exception e){
            System.out.println("invalid transaction type");
            return Response.status(400).entity(e).build();
        }
         
        //update account(s)
        myAccount.setBalance(newBal);
       
        
        //add transaction
        transaction1.setType(type);
        transaction1.setAmount(amount);
        transaction1.setAccount(myAccount);
        transaction1.setCustomer(c);
        transaction1.setPost_balance(newBal);
        transaction1.setdNow(date);
        list.add(transaction1);
        
        myAccount.setTransactions(list);
        entityManager.persist(transaction1);
        entityManager.getTransaction().commit();
        entityManager.close();

        return Response.status(200).entity("success!").build();
        
    }    
}

