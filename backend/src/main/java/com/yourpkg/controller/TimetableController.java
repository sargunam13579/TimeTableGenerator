package com.yourpkg.controller;

import org.springframework.web.bind.annotation.*;

import com.yourpkg.entity.Subject;
import com.yourpkg.service.csp.CSPSolver;

import java.util.*;

@RestController
@CrossOrigin
public class TimetableController {

    @PostMapping("/generate")
    public Map<String, Object> generate(@RequestBody List<Subject> subjects) {
        CSPSolver solver = new CSPSolver(subjects);
        List<Map<String, String[]>> all = solver.solveAll();
        Map<String, Object> res = new HashMap<>();
        res.put("count", all.size());
        res.put("timetables", all);
        return res;
    }
}