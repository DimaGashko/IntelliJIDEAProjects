package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();
        System.out.println("Hello");
    }

}
