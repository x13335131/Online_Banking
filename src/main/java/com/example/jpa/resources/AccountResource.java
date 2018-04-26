/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jpa.resources;

import com.example.jpa.models.Account;
import com.example.jpa.services.AccountService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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


@Path("/account")
public class AccountResource {
    EntityManager entityManager;
    AccountService acc_serv = new AccountService();
     
    /*
    GET AN ACCOUNT
    curl -v -H "Accept: application/json" -H "API_KEY:VALID_KEY" http://localhost:8080/api/account/1
    */
     @GET
    @Path("{id}")
     @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Account getAccount(@PathParam("id") int id) {
         return acc_serv.getAccount(id);
     }
      /*
    GET BALANCE
    curl -v -H "Accept: application/json" -H "API_KEY:VALID_KEY" http://localhost:8080/api/account/1/balance/3"
    */
      @GET
    @Path("{id}/balance/{accountNum}")
    public double getBalance(@PathParam("id") int id, @PathParam("accountNum") int accountNum){
        return acc_serv.getBalance(accountNum);
    }
    /*
    CREATE AN ACCOUNT
    curl -v -X POST -H "API_KEY:VALID_KEY" "http://localhost:8080/api/account/2/create?balance=80.00&sort_code=99"
    */
     @POST
    @Path("/{id}/create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
      public Response createAccount(@PathParam("id") int id, @QueryParam("balance") double balance, @QueryParam("sort_code") int sort_code) {
          return acc_serv.createAccount(id, balance, sort_code);
      }
    /*
    DELETING AN ACCOUNT
    curl -v -X DELETE -H "API_KEY:VALID_KEY" http://localhost:8080/api/account/delete/1
    */
    @DELETE
    @Path("delete/{accountNum}")
    public Response deleteAccount(@PathParam("accountNum") int accountNum) {
        return acc_serv.deleteAccount(accountNum);
    }
}
