package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;
import com.yourpkg.entity.Section;
import com.yourpkg.repository.SectionRepository;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionRepository sectionRepository;

    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @PostMapping
    public Section createSection(@RequestBody Section section) {
        return sectionRepository.save(section);
    }

    @GetMapping
    public java.util.List<Section> getAllSections() {
        return sectionRepository.findAll();
    }
}