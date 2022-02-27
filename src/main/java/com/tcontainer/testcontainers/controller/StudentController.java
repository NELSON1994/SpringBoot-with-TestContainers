package com.tcontainer.testcontainers.controller;

import com.tcontainer.testcontainers.entity.Student;
import com.tcontainer.testcontainers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
