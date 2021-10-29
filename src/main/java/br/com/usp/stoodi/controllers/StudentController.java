package br.com.usp.stoodi.controllers;

import br.com.usp.stoodi.models.Student;
import br.com.usp.stoodi.repositories.StudentRepository;
import br.com.usp.stoodi.requests.CreateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        var users = repository.findAll();

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid CreateStudentRequest request){
        var entity = request.toEntity();

        var isEmailAlreadyTaken = repository.existsByEmail(entity.getEmail());

        if(isEmailAlreadyTaken){
            return ResponseEntity.badRequest().build();
        }

        var createdTeacher = repository.save(entity);

        return ResponseEntity.ok(createdTeacher);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable("id") Long id){
        var deletedUser = repository.findById(id);

        if(deletedUser.isPresent()){
            var teacher = deletedUser.get();
            repository.deleteById(id);

            return ResponseEntity.ok(teacher);
        }

        return ResponseEntity.badRequest().build();
    }
}
