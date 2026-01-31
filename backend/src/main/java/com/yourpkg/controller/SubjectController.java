package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yourpkg.entity.Subject;
import com.yourpkg.repository.SubjectRepository;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PostMapping
    public Subject create(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @GetMapping
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }
}