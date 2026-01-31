package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.College;

public interface CollegeRepository extends JpaRepository<College, Long> {
}