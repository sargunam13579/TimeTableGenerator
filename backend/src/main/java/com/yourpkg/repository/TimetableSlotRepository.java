package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.TimetableSlot;
import java.util.List;

public interface TimetableSlotRepository extends JpaRepository<TimetableSlot, Long> {

    boolean existsByTeacherIdAndDayAndPeriod(
        Long teacherId,
        String day,
        int period
    );
    boolean existsBySectionIdAndDayAndPeriod(
        Long sectionId,
        String day,
        int period
    );
    List<TimetableSlot> findBySectionIdAndDay(
        Long sectionId,
        String day
    );
    long countBySectionIdAndSubjectId(
        Long sectionId,
        Long subjectId
    );

}