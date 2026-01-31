package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
@Table(
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"teacher_id", "subject_id"})
  }
)
public class TeacherSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Subject subject;

    // getters & setters
}