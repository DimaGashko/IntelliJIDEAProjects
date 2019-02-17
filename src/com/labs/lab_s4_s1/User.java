package com.labs.lab_s4_s1;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String country;
    private boolean online;
    private LocalDate registered;
    private int age;

    private User() {

    }

    public User(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return online == user.online &&
                age == user.age &&
                Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(country, user.country) &&
                Objects.equals(registered, user.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, country, online, registered, age);
    }

    public UUID getId() {
        return id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 1) age = 1;
        else if (age > 200) age = 200;

        this.age = age;
    }
}
