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
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    public Subject getSubject() {
      return subject;
    }
    public void setSection(Section section2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setSection'");
    }
    public void setSubject(Subject subject2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSubject'");
    }
    public void setTeacher(Teacher teacher2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTeacher'");
    }
    public void setDay(String day2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDay'");
    }
    public void setPeriod(int period2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPeriod'");
    }
}