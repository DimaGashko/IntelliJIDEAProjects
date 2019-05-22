package com;

import dao.UserDao;
import lib.Auth.Auth;
import lib.Auth.AuthException;
import lib.Auth.LoginData;
import schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

import static security.PasswordUtils.hashPassword;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        UserDao userDao = new UserDao(em);

        Auth auth = new Auth(userDao);

        User user = new User();
        user.setFirstName("Dmitry");
        user.setLastName("Gashko");
        user.setUsername("DmitryGashko");
        user.setEmail("dimagashko@gmail.com");
        user.setPassword("qqqqqqqqww");

        try {
            auth.signup(user);
        } catch (AuthException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("Success");
    }

}
