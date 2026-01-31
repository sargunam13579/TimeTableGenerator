package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
}