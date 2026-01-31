package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;

import com.yourpkg.entity.Teacher;
import com.yourpkg.repository.TeacherRepository;


@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @GetMapping
    public java.util.List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}