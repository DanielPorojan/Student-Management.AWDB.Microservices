package com.grade.service.controller;

import com.grade.service.model.Grade;
import com.grade.service.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRepository repository;

    public GradeController(GradeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Grade> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Grade create(@RequestBody Grade grade) {
        return repository.save(grade);
    }
}