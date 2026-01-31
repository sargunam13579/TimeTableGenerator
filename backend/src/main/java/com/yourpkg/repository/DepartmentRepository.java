package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}