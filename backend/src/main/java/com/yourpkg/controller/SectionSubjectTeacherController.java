package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;

import com.yourpkg.entity.SectionSubjectTeacher;
import com.yourpkg.repository.SectionSubjectTeacherRepository;

@RestController
@RequestMapping("/api/section-subject-teacher")
public class SectionSubjectTeacherController {

    private final SectionSubjectTeacherRepository repo;

    public SectionSubjectTeacherController(SectionSubjectTeacherRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public SectionSubjectTeacher assign(@RequestBody SectionSubjectTeacher sst) {
        return repo.save(sst);
    }
}