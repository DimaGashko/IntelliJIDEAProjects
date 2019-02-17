package com.labs.lab_s4_s1;

import java.util.TreeSet;

public class Lab1 {
    public static void main(String[] args) {
        TreeSet<User> users = new TreeSet<>();

        users.add(new User("a", "a"));
        users.add(new User("b", "b"));
        users.add(new User("c", "cus" +
                ""));

        System.out.println(users);
    }
}
