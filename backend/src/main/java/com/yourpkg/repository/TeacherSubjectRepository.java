package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.TeacherSubject;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
}