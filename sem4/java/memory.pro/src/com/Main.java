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
    private EntityManager em;
    private UserDao userDao;
    private Auth auth;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();

        userDao = new UserDao(em);
        auth = new Auth(userDao);

        //isLoggedIn();
        //System.out.println(auth.logout());
        isLoggedIn();
        login("DmitryGashko", "qqqqqqqqww");
        isLoggedIn();
        login("DmitryGashko", "qqqqqqqqww");
        isLoggedIn();
        login("Kotlin", "kotlinkotlin");
        isLoggedIn();

        //signup();
    }

    private void login(String username, String password) {
        try {
            auth.login(username, password);
            System.out.println("Success!");
        } catch (AuthException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void isLoggedIn() {
        System.out.println(auth.isLoggedIn() ? "You're online" : "You're offline");
    }

    private void signup() {
        User user = new User();
        user.setFirstName("Jon");
        user.setLastName("Kotlin");
        user.setUsername("Kotlin");
        user.setEmail("kotlinjon@gmail.com");
        user.setPassword("kotlinkotlin");

        try {
            auth.signup(user);
        } catch (AuthException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Success");
    }

}
