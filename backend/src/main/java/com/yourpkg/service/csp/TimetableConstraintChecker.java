package com.yourpkg.service.csp;

import org.springframework.stereotype.Component;
import java.util.*;
import com.yourpkg.entity.TimetableSlot;
import com.yourpkg.repository.SectionSubjectTeacherRepository;
import com.yourpkg.repository.TimetableSlotRepository;

@Component
public class TimetableConstraintChecker {

    private final TimetableSlotRepository timetableSlotRepository;
    private final SectionSubjectTeacherRepository sectionSubjectTeacherRepository;

    public TimetableConstraintChecker(
            TimetableSlotRepository timetableSlotRepository,
            SectionSubjectTeacherRepository sectionSubjectTeacherRepository) {

        this.timetableSlotRepository = timetableSlotRepository;
        this.sectionSubjectTeacherRepository = sectionSubjectTeacherRepository;
    }

    // ================= RULE 1 =================
    // Teacher must be free
    public boolean isTeacherFree(Long teacherId, String day, int period) {
        return !timetableSlotRepository
                .existsByTeacherIdAndDayAndPeriod(teacherId, day, period);
    }

    // ================= RULE 2 =================
    // Section must be free
    public boolean isSectionFree(Long sectionId, String day, int period) {
        return !timetableSlotRepository
                .existsBySectionIdAndDayAndPeriod(sectionId, day, period);
    }

    // ================= RULE 3 =================
    // One subject → one teacher per section
    public boolean canAssignSubject(Long sectionId, Long subjectId) {
        return !sectionSubjectTeacherRepository
                .existsBySectionIdAndSubjectId(sectionId, subjectId);
    }

    // ================= RULE 4 =================
    // Lab must be continuous 4 periods (1–4 or 5–8)
    public boolean canPlaceLab(
            Long sectionId,
            Long teacherId,
            String day,
            int startPeriod
    ) {
        if (startPeriod != 1 && startPeriod != 5) {
            return false;
        }

        for (int p = startPeriod; p < startPeriod + 4; p++) {
            if (!isSectionFree(sectionId, day, p)) {
                return false;
            }
            if (!isTeacherFree(teacherId, day, p)) {
                return false;
            }
        }
        return true;
    }

    // ================= RULE 5 =================
    // Theory distribution rules
    public boolean canPlaceTheorySubject(
            Long sectionId,
            Long subjectId,
            String day,
            boolean isLabSubject
    ) {
        List<TimetableSlot> slots =
                timetableSlotRepository.findBySectionIdAndDay(sectionId, day);
        // Count occurrences per subject
        Map<Long, Integer> subjectCountMap = new HashMap<>();
        for (TimetableSlot slot : slots) {
            Long sid = slot.getSubject().getId();
            subjectCountMap.put(sid, subjectCountMap.getOrDefault(sid, 0) + 1);
        }
        int sameSubjectCount = subjectCountMap.getOrDefault(subjectId, 0);
        boolean anySubjectAlreadyTwice =
                subjectCountMap.values().stream().anyMatch(c -> c >= 2);
        // ---- LAB + THEORY rule ----
        if (isLabSubject) {
            // theory allowed only once on lab day
            return sameSubjectCount == 0;
        }
        // ---- Normal theory rules ----
        if (sameSubjectCount >= 2) {
            return false; // max 2 per day
        }
        if (sameSubjectCount == 1 && anySubjectAlreadyTwice) {
            return false; // only ONE subject can be twice per day
        }
        return true;
    }

    // ================= RULE 6 =================
    // Subject must not exceed weekly hours
    public boolean canPlaceWeeklySubject(
            Long sectionId,
            Long subjectId,
            int hoursPerWeek
    ) {
        long currentCount =
                timetableSlotRepository.countBySectionIdAndSubjectId(
                        sectionId, subjectId
                );

        return currentCount < hoursPerWeek;
    }
}