package com.labs.lab_s4_1;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class User implements Comparable<User>, Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private boolean online;
    private LocalDate registered;
    private int age;

    private User() {

    }

    public User(String id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User(String id, String firstName, String lastName, String country, int age, boolean online, LocalDate registered) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCountry(country);
        setOnline(online);
        setRegistered(registered);
        setAge(age);
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return String.format(
                "{name: %s, country: %s, age: %d, online: %b, registered: %s}",
                getFullName(), country, age, online, registered
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return online == user.online &&
                age == user.age &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(country, user.country) &&
                Objects.equals(registered, user.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, country, online, registered, age);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    private void setRegistered(LocalDate registered) {
        this.registered = registered;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 1) age = 1;
        else if (age > 200) age = 200;

        this.age = age;
    }

    @Override
    public int compareTo(@NotNull User o) {
        return Comparator.comparing(User::getLastName, String::compareTo)
                .thenComparing(User::getFirstName, String::compareTo)
                .thenComparingInt(User::getAge).compare(this, o);

    }
}
