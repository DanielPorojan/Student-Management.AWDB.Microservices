package com.professor.service.controller;

import com.professor.service.model.Professor;
import com.professor.service.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorRepository repository;

    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Professor> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Professor create(@RequestBody Professor professor) {
        return repository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor update(@PathVariable Long id, @RequestBody Professor updatedProfessor) {
        Optional<Professor> optionalProfessor = repository.findById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setFirstName(updatedProfessor.getFirstName());
            professor.setLastName(updatedProfessor.getLastName());
            return repository.save(professor);
        } else {
            throw new RuntimeException("Professor not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}