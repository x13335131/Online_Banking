/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jpa.resources;

import com.example.jpa.services.TransactionService;
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
@Path("/transaction")
public class TransactionResource {
    
    TransactionService trans_serv = new TransactionService();
    
    /* 
    POST
    curl -v -X POST -H "API_KEY:VALID_KEY" "http://localhost:8080/api/transaction/2/transfer?type=lodgement&amount=3000.00&recieverAccount=0"
    curl -v -X POST -H "API_KEY:VALID_KEY" "http://localhost:8080/api/transaction/12/transfer?type=withdrawal&amount=1000.00&recieverAccount=0"
    curl -v -X POST -H "API_KEY:VALID_KEY" "http://localhost:8080/api/transaction/12/transfer?type=transfer&amount=20.00&recieverAccount=10"
    */
    @POST
    @Path("{accountNum}/transfer")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response transaction(@PathParam("accountNum") int accountNum, @QueryParam("type") String type,@QueryParam("amount") double amount,@QueryParam("recieverAccount") int recieverAccount) { 
        return trans_serv.transaction(accountNum, type, amount, recieverAccount);
    } 
   
        
}
