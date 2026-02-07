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
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject getSubject() {
        return subject;
    }
}