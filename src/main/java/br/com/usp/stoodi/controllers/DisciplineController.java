package br.com.usp.stoodi.controllers;

import br.com.usp.stoodi.models.Discipline;
import br.com.usp.stoodi.repositories.*;
import br.com.usp.stoodi.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("{id}")
    public ResponseEntity<Discipline> getDiscipline(@PathVariable("id") Long id){
        var possibleDiscipline = repository.findById(id);

        if(possibleDiscipline.isPresent()){
            var discipline = possibleDiscipline.get();

            return ResponseEntity.ok(discipline);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<Discipline> removeDiscipline(@PathVariable("id") Long id){
        var possibleDiscipline = repository.findById(id);

        if(possibleDiscipline.isPresent()){
            var discipline = possibleDiscipline.get();
            repository.delete(discipline);
            return ResponseEntity.ok(discipline);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("{teacher_id}")
    public ResponseEntity<Discipline> createDiscipline(@PathVariable("teacher_id") Long teacherId, @RequestBody @Valid CreateDisciplineRequest request){
        var possibleTeacher = teacherRepository.findById(teacherId);

        if(possibleTeacher.isPresent()) {
            var discipline = request.toEntity();
            discipline.setTeacher(possibleTeacher.get());

            var createdDiscipline = repository.save(discipline);

            return ResponseEntity.ok(createdDiscipline);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable("id") Long id, @RequestBody UpdateDisciplineRequest request){
        var possibleDiscipline = repository.findById(id);

        if(possibleDiscipline.isPresent()) {
            var discipline = possibleDiscipline.get();

            request.updateDiscipline(discipline);

            repository.save(discipline);

            return ResponseEntity.ok(discipline);
        }

        return ResponseEntity.badRequest().build();
    }



}
