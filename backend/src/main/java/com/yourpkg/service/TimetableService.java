package com.yourpkg.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yourpkg.entity.Section;
import com.yourpkg.entity.Subject;
import com.yourpkg.entity.Teacher;
import com.yourpkg.entity.TeacherSubject;
import com.yourpkg.entity.TimetableSlot;
import com.yourpkg.repository.SectionRepository;
import com.yourpkg.repository.SubjectRepository;
import com.yourpkg.repository.TeacherSubjectRepository;
import com.yourpkg.repository.TimetableSlotRepository;
import com.yourpkg.service.csp.TimetableConstraintChecker;

@Service
public class TimetableService {
    private final TimetableConstraintChecker checker;
    private final TimetableSlotRepository timetableSlotRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private static final String[] DAYS = {"MON", "TUE", "WED", "THU", "FRI"};
    private static final int PERIODS_PER_DAY = 8;
    // ✅ CORRECT CONSTRUCTOR
    public TimetableService(
            TimetableConstraintChecker checker,
            TimetableSlotRepository timetableSlotRepository,
            SectionRepository sectionRepository,
            SubjectRepository subjectRepository,
            TeacherSubjectRepository teacherSubjectRepository) {
        this.checker = checker;
        this.timetableSlotRepository = timetableSlotRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherSubjectRepository = teacherSubjectRepository;
    }
    // ======================================================
    // MAIN ENTRY POINT
    // ======================================================
    @Transactional
    public void generateTimetableForSection(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        List<Subject> subjects = subjectRepository.findAll();
        for (String day : DAYS) {
            for (int period = 1; period <= PERIODS_PER_DAY; period++) {
                if (!checker.isSectionFree(sectionId, day, period)) {
                    continue;
                }
                boolean placed = false;
                for (Subject subject : subjects) {
                    Long subjectId = subject.getId();
                    int hoursPerWeek = subject.getHoursPerWeek();
                    boolean isLab = subject.isLab();
                    // RULE‑6
                    if (!checker.canPlaceWeeklySubject(sectionId, subjectId, hoursPerWeek)) {
                        continue;
                    }
                    Teacher teacher = findTeacherForSubject(subjectId);
                    if (teacher == null) continue;
                    Long teacherId = teacher.getId();
                    // ================= LAB =================
                    if (isLab) {
                        if (period != 1 && period != 5) continue;
                        if (!checker.canPlaceLab(sectionId, teacherId, day, period)) continue;
                        if (!checker.canPlaceTheorySubject(sectionId, subjectId, day, true)) continue;
                        for (int p = period; p < period + 4; p++) {
                            saveSlot(section, subject, teacher, day, p);
                        }
                        placed = true;
                        break;
                    }
                    // ================= THEORY =================
                    else {
                        if (!checker.isTeacherFree(teacherId, day, period)) continue;
                        if (!checker.isSectionFree(sectionId, day, period)) continue;
                        if (!checker.canAssignSubject(sectionId, subjectId)) continue;
                        if (!checker.canPlaceTheorySubject(sectionId, subjectId, day, false)) continue;

                        saveSlot(section, subject, teacher, day, period);
                        placed = true;
                        break;
                    }
                }
                if (!placed) {
                    // free period
                }
            }
        }
    }
    // ======================================================
    // HELPER METHODS
    // ======================================================
    private void saveSlot(
            Section section,
            Subject subject,
            Teacher teacher,
            String day,
            int period) {
        TimetableSlot slot = new TimetableSlot();
        slot.setSection(section);
        slot.setSubject(subject);
        slot.setTeacher(teacher);
        slot.setDay(day);
        slot.setPeriod(period);

        timetableSlotRepository.save(slot);
    }
    // ✅ FIXED METHOD
    private Teacher findTeacherForSubject(Long subjectId) {
        return teacherSubjectRepository
                .findFirstBySubjectId(subjectId)
                .map(TeacherSubject::getTeacher)
                .orElse(null);
    }
}