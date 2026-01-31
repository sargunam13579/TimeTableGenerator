package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;

import com.yourpkg.entity.TeacherSubject;
import com.yourpkg.repository.TeacherSubjectRepository;
@RestController
@RequestMapping("/api/teacher-subject")
public class TeacherSubjectController {

    private final TeacherSubjectRepository repo;

    public TeacherSubjectController(TeacherSubjectRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public TeacherSubject assign(@RequestBody TeacherSubject ts) {
        return repo.save(ts);
    }
}