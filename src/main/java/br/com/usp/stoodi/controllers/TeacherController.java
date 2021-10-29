package br.com.usp.stoodi.controllers;

import br.com.usp.stoodi.models.Teacher;
import br.com.usp.stoodi.repositories.TeacherRepository;
import br.com.usp.stoodi.requests.CreateTeacherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository repository;

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        var users = repository.findAll();

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid CreateTeacherRequest request){
        var entity = request.toEntity();

        var isEmailAlreadyTaken = repository.existsByEmail(entity.getEmail());

        if(isEmailAlreadyTaken){
            return ResponseEntity.badRequest().build();
        }

        var createdTeacher = repository.save(entity);

        return ResponseEntity.ok(createdTeacher);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Teacher> removeTeacher(@PathVariable("id") Long id){
        var deletedUser = repository.findById(id);

        if(deletedUser.isPresent()){
            var teacher = deletedUser.get();
            repository.deleteById(id);

            return ResponseEntity.ok(teacher);
        }

        return ResponseEntity.badRequest().build();
    }



}
