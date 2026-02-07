package com.yourpkg.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findAnyBySubjects_Id(Long subjectId);
}