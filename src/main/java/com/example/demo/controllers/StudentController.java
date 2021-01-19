package com.example.demo.controllers;

import com.example.demo.dto.StudentDTO;
import com.example.demo.services.impl.StudentServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    private StudentServiceImpl studentServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO selectStudent(@PathVariable("id") Integer id) {
        return studentServiceImpl.findOne(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getAllStudents() {
        return studentServiceImpl.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentServiceImpl.save(studentDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO updateStudent(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return studentServiceImpl.update(id, studentDTO);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@PathVariable("id") Integer id) {
        studentServiceImpl.remove(id);
    }

    @GetMapping(value = "/{accountName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO findByAccountName(@PathVariable("accountName") String accountName, @PathVariable("password") String password) {
        return studentServiceImpl.findByAccountNameAndPassword(accountName, password);
    }
}