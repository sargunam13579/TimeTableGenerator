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

    public void setName(String valueOf) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }

    public void setYear(Integer year2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setYear'");
    }

    public void setDepartment(Department dept) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDepartment'");
    }

    // getters & setters
}