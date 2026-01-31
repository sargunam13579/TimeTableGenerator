package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.TimetableSlot;

public interface TimetableSlotRepository extends JpaRepository<TimetableSlot, Long> {
}