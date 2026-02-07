package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    public String name;
    private String type;           // THEORY / LAB
    private int hoursPerWeek;

    public Integer weekly;

    public boolean isLab;

    // getters & setters
    public Long getId() {
        return id;
    }

    public boolean isLab() {
        return isLab;
    }

    public int getHoursPerWeek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHoursPerWeek'");
    }
}