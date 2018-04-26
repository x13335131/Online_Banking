package com.example.jpa.runnables;

import com.example.jpa.models.Transaction;
import com.example.jpa.models.Customer;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransferExample {
        public static void main(String[] args) {
            
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Customer cmr = new Customer("Max", "max@gmail.com", "12 berkley meadows", 8888 );

        entitymanager.persist(cmr);

        Transaction trans1 = new Transaction();
        Transaction trans2 = new Transaction();
        Transaction trans3 = new Transaction();
        Transaction trans4 = new Transaction();
        
        trans1.setType("withdrawl");
        trans1.setAmount(200);
        
        trans2.setType("lodgement");
        trans2.setAmount(100);
        
        trans3.setType("withdrawal");
        trans3.setAmount(20);
        
        trans4.setType("lodgement");
        trans4.setAmount(1000);
        

        trans1.setCustomer(cmr);
        trans2.setCustomer(cmr);
        trans3.setCustomer(cmr);
        trans4.setCustomer(cmr);
        
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(trans1);
        list.add(trans2);
        list.add(trans3);
        list.add(trans4);
        
        cmr.setTransactions(list);
        entitymanager.persist(cmr);
        
        entitymanager.getTransaction().commit();
        
        // retrive the user from the database
        Customer test = entitymanager.find(Customer.class, 1);

        // the retreived user has a filled arraylist of transfers which belong to this user
        for (Transaction transaction : test.getTransactions()) {
            System.out.println(transaction);
        }
        
        entitymanager.close();
        emfactory.close();

    }
}
