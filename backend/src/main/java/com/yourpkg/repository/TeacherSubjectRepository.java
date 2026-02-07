package com.yourpkg.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.TeacherSubject;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
    Optional<TeacherSubject> findFirstBySubjectId(Long subjectId);
}