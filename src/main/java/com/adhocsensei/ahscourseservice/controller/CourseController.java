package com.adhocsensei.ahscourseservice.controller;

import com.adhocsensei.ahscourseservice.dao.CourseRepository;
import com.adhocsensei.ahscourseservice.dao.UserRepository;
import com.adhocsensei.ahscourseservice.dto.Course;
import com.adhocsensei.ahscourseservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/course")
    public List<Course> getAllCourses(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String category,
                                      @RequestParam(required = false) String location,
                                      @RequestParam(required = false) String date) {
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
        List<Course> allCourses = courseRepo.findAll();
        return allCourses;
    }

    @GetMapping("/course/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseRepo.findById(id);
    }

    @PostMapping("/senseidash/{id}/course")
    public Course createCourse(@PathVariable Long id, @RequestBody Course course) {
        User user = userRepo.getById(id);
        course.setSenseiId(user.getId());
        course.setUser(user);
        Course savedCourse = courseRepo.save(course);
        Long cId = savedCourse.getId();
        Course desiredCourse = courseRepo.getById(cId);
        return desiredCourse;
    }

    @PutMapping("/course/{id}")
    public void updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isPresent()) {
            course.setId(id);
            courseRepo.save(course);
        }
    }

    @DeleteMapping("/course/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        courseRepo.delete(courseRepo.getById(id));
    }
}