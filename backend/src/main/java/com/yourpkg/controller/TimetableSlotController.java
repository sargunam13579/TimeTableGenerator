package com.yourpkg.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yourpkg.entity.TimetableSlot;
import com.yourpkg.repository.TimetableSlotRepository;

@RestController
@RequestMapping("/api/timetable-slots")
@CrossOrigin
public class TimetableSlotController {

    private final TimetableSlotRepository timetableSlotRepository;

    public TimetableSlotController(TimetableSlotRepository timetableSlotRepository) {
        this.timetableSlotRepository = timetableSlotRepository;
    }

    @GetMapping
    public List<TimetableSlot> getAllSlots() {
        return timetableSlotRepository.findAll();
    }
}