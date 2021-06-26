package com.adhocsensei.ahscourseservice.controller;

import com.adhocsensei.ahscourseservice.dao.CourseRepository;
import com.adhocsensei.ahscourseservice.dto.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepo;

    @GetMapping("/course")
    public List<Course> getAllCourses(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String category,
                                      @RequestParam(required = false) String location,
                                      @RequestParam(required = false) String date,
                                      @RequestParam(required = false) Long senseiId) {
        if (title != null) {
            return courseRepo.findByTitle(title);
        }
        else if (category != null) {
            return courseRepo.findByCategory(category);
        }
        else if (location != null) {
            return courseRepo.findByLocation(location);
        }
        else if (date !=null) {
            return courseRepo.findByDate(date);
        }
        else if (senseiId !=null) {
            return courseRepo.findBySenseiId(senseiId);
        }
        return courseRepo.findAll();
    }

    @GetMapping("/course/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseRepo.findById(id);
    }

    @PostMapping("/course")
    public Course createCourse(@RequestBody Course course) {
        return courseRepo.save(course);
    }

    @PutMapping("/course/{id}")
    public void updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isPresent()) {
            course.setCourseId(id);
            courseRepo.save(course);
        }
    }

    @DeleteMapping("/course/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        courseRepo.deleteById(id);
    }
}