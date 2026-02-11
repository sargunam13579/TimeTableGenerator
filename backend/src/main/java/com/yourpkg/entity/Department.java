package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    private College college;

    // getters & setters
}