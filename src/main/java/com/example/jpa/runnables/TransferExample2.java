package com.example.jpa.runnables;

import com.example.jpa.models.Transaction;
import com.example.jpa.models.Customer;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransferExample2 {
    /*
        public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        User user = new User("John", 100);

        entitymanager.persist(user);

        Transfer trans1 = new Transfer();
        Transfer trans2 = new Transfer();
        Transfer trans3 = new Transfer();
        Transfer trans4 = new Transfer();
        
        trans1.setType("Debit");
        trans1.setAmount(200);
        
        trans2.setType("Credit");
        trans2.setAmount(100);
        
        trans3.setType("Credit");
        trans3.setAmount(20);
        
        trans4.setType("Debit");
        trans4.setAmount(1000);
        

        trans1.setUser(user);
        trans2.setUser(user);
        trans3.setUser(user);
        trans4.setUser(user);
        
        ArrayList<Transfer> list = new ArrayList<>();
        list.add(trans1);
        list.add(trans2);
        list.add(trans3);
        list.add(trans4);
        
        user.setTransactions(list);
        entitymanager.persist(user);
        
        entitymanager.getTransaction().commit();
        
        // retrive the user from the database
        User test = entitymanager.find(User.class, 2);

        // the retreived user has a filled arraylist of transfers which belong to this user
        for (Transfer transaction : test.getTransfers()) {
            System.out.println(transaction);
        }
        
        entitymanager.close();
        emfactory.close();
    }*/
}
