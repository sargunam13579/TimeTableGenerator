package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.SectionSubjectTeacher;

public interface SectionSubjectTeacherRepository
        extends JpaRepository<SectionSubjectTeacher, Long> {
}