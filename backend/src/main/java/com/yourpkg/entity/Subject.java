package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String type;           // THEORY / LAB
    private int hoursPerWeek;

    // getters & setters
}