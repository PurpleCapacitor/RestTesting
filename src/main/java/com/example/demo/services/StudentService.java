package com.example.demo.services;

import com.example.demo.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO findOne(Integer id);
    List<StudentDTO> findAll();
    StudentDTO save(StudentDTO dto);
    void remove(Integer id) throws IllegalArgumentException;
    StudentDTO update(Integer id, StudentDTO dto);
    StudentDTO findByAccountNameAndPassword(String accountName, String password);
}
