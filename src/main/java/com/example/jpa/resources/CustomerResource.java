/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jpa.resources;

import com.example.jpa.models.Customer;
import com.example.jpa.services.CustomerService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Karolina
 */
@Path("/customers")
public class CustomerResource {
    
    CustomerService cust_serv = new CustomerService();
    /*
    GETTING a customer
    curl -v -H "Accept: application/xml" -H "API_KEY:VALID_KEY" http://localhost:8080/api/customers/4
    */
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
    return cust_serv.getCustomer(id);
    }
     /*
    CREATING a new customer
    curl -v -H "Accept: application/xml" -H "API_KEY:VALID_KEY" -H "Content-type: application/json" http://localhost:8080/api/customers/create -d '{"name":"karry","email":"kar@gmail.com","address":"10 grove street","credentials":700}'
    */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})    
    public Response createCustomer(Customer c) {
        return cust_serv.createCustomer(c);
    }
    
      /*
    UPDATING customer
    curl -X PUT -H "API_KEY:VALID_KEY" -H "Content-type: application/json" http://localhost:8080/api/customers/update/1 -d '{"name":"olie","email":"lou@gmail.com","address":"4 grove road","credentials":1234}'
    */
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("update/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer newC) {
        return cust_serv.updateCustomer(id, newC);
    }
    
    /*
    DELETING customer
    curl -v -X DELETE -H "API_KEY:VALID_KEY" http://localhost:8080/api/customers/delete/3
    */
    
    @DELETE
    @Path("delete/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        return cust_serv.deleteCustomer(id);
    }
}
