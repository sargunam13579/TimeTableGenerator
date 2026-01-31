package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;
import com.yourpkg.entity.Department;
import com.yourpkg.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @GetMapping
    public java.util.List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}