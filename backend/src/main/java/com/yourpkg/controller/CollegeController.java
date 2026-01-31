package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;
import com.yourpkg.entity.College;
import com.yourpkg.repository.CollegeRepository;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

    private final CollegeRepository collegeRepository;

    public CollegeController(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @PostMapping
    public College createCollege(@RequestBody College college) {
        return collegeRepository.save(college);
    }

    @GetMapping
    public java.util.List<College> getAllColleges() {
        return collegeRepository.findAll();
    }
}