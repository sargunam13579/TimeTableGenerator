package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}