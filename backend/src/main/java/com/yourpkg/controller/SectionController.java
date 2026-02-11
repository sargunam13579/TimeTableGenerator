package com.yourpkg.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.yourpkg.entity.Department;
import com.yourpkg.entity.Section;
import com.yourpkg.repository.DepartmentRepository;
import com.yourpkg.repository.SectionRepository;
import com.yourpkg.dto.SectionRequest;

@RestController
@RequestMapping("/api/sections")
@CrossOrigin
public class SectionController {
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    public SectionController(SectionRepository sectionRepository, DepartmentRepository departmentRepository) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
    }
    @PostMapping("/bulk")
    public List<Section> createSections(@RequestBody SectionRequest request) {

        List<Section> sections = new ArrayList<>();

        Department dept = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        for (int i = 0; i < request.getCount(); i++) {

            Section section = new Section();
            section.setName(String.valueOf((char) ('A' + i)));
            section.setYear(request.getYear());
            section.setDepartment(dept);

            sections.add(sectionRepository.save(section));
        }

        return sections;
    }

    @GetMapping
    public List<Section> getAllSections(){ return sectionRepository.findAll(); }
}