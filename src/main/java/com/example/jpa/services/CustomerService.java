package com.example.jpa.services;

import com.example.jpa.models.Transaction;
import com.example.jpa.models.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class CustomerService {

    EntityManager entityManager;

    //constructor
    public CustomerService() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        entityManager = emfactory.createEntityManager();
    }
    
    /*
        GET CUSTOMER
    */
   
    public Customer getCustomer(int id) {
        try{Customer test = entityManager.find(Customer.class, id);
        System.out.println(test);
        return test;
        } catch(Exception e){
           System.out.println("Exception occurred"+e);
           return null;
       }
    }
    
    /*
        CREATE CUSTOMER
    */

    public Response createCustomer(Customer c) {
       try{ System.out.println(c);     
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("************New customer created***********");
        return Response.status(200).entity(c).build();
       } catch(Exception e){
           System.out.println("Exception occurred"+e);
            return Response.status(400).entity(c).build();
       }
    }
    
      /*
        UPDATE CUSTOMER
      */
    
    public Response updateCustomer(int id, Customer newC) {
      
        Customer c = entityManager.find(Customer.class, id);
       try{ if (c != null) {
            entityManager.getTransaction().begin();
            c.setName(newC.getName());
            c.setEmail(newC.getEmail());
            c.setAddress(newC.getAddress());
            c.setCredentials(newC.getCredentials());
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("************Update customer***********");
        }
        return Response.status(200).entity(c).build();
       }catch(Exception e){
            System.out.println("Exception occurred"+e);
            return Response.status(400).entity(c).build(); //bad request
       }
    }
    
    /*
        DELETING CUSTOMER
    */
   
    public Response deleteCustomer(int id) {
       try{ System.out.println("************Deleting customer***********");
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();

        return Response.status(Response.Status.ACCEPTED).build();
    }catch(Exception e){
            System.out.println("Exception occurred"+e);
             return Response.status(Response.Status.BAD_REQUEST).build();
       }
    }
}
