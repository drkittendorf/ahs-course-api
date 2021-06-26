package com.adhocsensei.ahscourseservice.dao;

import com.adhocsensei.ahscourseservice.dto.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitle(String title);
    List<Course> findByCategory(String category);
    List<Course> findByLocation(String location);
    List<Course> findByDate(String date);
    List<Course> findBySenseiId(Long senseiId);
//    find created courses by user
}