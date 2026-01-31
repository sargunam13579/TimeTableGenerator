package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
@Table(
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "year", "department_id"})
  }
)
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // A, B, C
    private int year;      // 1,2,3,4

    @ManyToOne
    private Department department;

    // getters & setters
}