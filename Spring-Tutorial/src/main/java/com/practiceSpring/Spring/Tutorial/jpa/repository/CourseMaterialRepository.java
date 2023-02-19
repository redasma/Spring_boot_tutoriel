package com.practiceSpring.Spring.Tutorial.jpa.repository;

import com.practiceSpring.Spring.Tutorial.jpa.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
