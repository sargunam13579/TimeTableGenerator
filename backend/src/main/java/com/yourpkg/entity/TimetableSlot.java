package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
@Table(
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"section_id", "day", "period"}),
    @UniqueConstraint(columnNames = {"teacher_id", "day", "period"})
  }
)
public class TimetableSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;    // MON, TUE...
    private int period;   // 1–8

    @ManyToOne
    private Section section;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    // getters & setters
}