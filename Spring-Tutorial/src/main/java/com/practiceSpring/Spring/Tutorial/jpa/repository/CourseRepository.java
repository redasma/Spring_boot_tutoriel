package com.practiceSpring.Spring.Tutorial.jpa.repository;

import com.practiceSpring.Spring.Tutorial.jpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
