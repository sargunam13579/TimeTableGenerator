package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}