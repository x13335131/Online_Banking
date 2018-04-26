/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jpa.services;

import com.example.jpa.models.Account;
import com.example.jpa.models.Customer;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kieran
 */
public class AccountService {
     EntityManager entityManager;

     
    public AccountService() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        entityManager = emfactory.createEntityManager();
    }
 
    public Account getAccount(int id) {
        Account test = entityManager.find(Account.class, id);
        if (test == null) {
            throw new NotFoundException("error in getting account");
        }
        return test;
    }
      public double getBalance(int acc_no) {
        Account a = entityManager.find(Account.class, acc_no);
        double bal=a.getBalance();
        entityManager.close();
        return bal;
    }
     //@POST
    //@Path("/{id}/create")
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
      public Response createAccount( int id, double balance, int sort_code) {
        
        Customer customer = entityManager.find(Customer.class, id);
        
        entityManager.getTransaction().begin();
        //Account a= new Account(accountNum, balance, sort_code, customer);
        Account a= new Account();
        
        a.setBalance(balance);
        a.setSort_code(sort_code);
        a.setCustomer(customer);
        
        ArrayList<Account> list = new ArrayList<>();
        list.add(a);
        
        customer.setAccounts(list);    
        entityManager.persist(a);
        entityManager.getTransaction().commit();
        entityManager.close();

        return Response.status(200).entity("account created").build();
    }
    
    /*
    DELETING AN ACCOUNT
    curl -v -X DELETE -H "API_KEY:VALID_KEY" http://localhost:8080/api/account/delete/1
    */
 
    
    
   
    public Response deleteAccount( int accountNum) {
        System.out.println("************Deleting customer***********");
        Account a = entityManager.find(Account.class, accountNum);
        int test=a.getAccountNum();
        System.out.println("accountNum:"+test);
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
