package com.labs.lab_s4_s1;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private final UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String country;
    private boolean online;
    private LocalDate registered;
    private int age;

}
