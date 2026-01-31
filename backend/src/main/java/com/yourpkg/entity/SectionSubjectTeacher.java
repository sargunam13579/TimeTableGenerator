package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
@Table(
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"section_id", "subject_id"}),
    @UniqueConstraint(columnNames = {"section_id", "teacher_id"})
  }
)
public class SectionSubjectTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Section section;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    // getters & setters
}