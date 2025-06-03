package com.course.service.controller;

import com.course.service.model.Course;
import com.course.service.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Course> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return repository.save(course);
    }
}