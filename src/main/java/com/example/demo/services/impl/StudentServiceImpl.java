package com.example.demo.services.impl;

import com.example.demo.dto.StudentDTO;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.models.Student;
import com.example.demo.repos.StudentRepository;
import com.example.demo.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    // DI constructor
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDTO findOne(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following id = " + id + " is not found.");
        }
        return studentMapper.transformToDTO(student.get());
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(i -> studentMapper.transformToDTO(i)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.transformToEntity(studentDTO);
        return studentMapper.transformToDTO(studentRepository.save(student));
    }

    @Override
    public void remove(Integer id) throws IllegalArgumentException {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following id = " + id + " is not found.");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO update(Integer id, StudentDTO studentDTO) {
        Optional<Student> oldStudent = studentRepository.findById(id);
        if (!oldStudent.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following id = " + id + " is not found.");
        }
        oldStudent.get().setName(studentDTO.getName());
        oldStudent.get().setAccountName(studentDTO.getAccountName());
        oldStudent.get().setPassword(studentDTO.getPassword());
        studentRepository.save(oldStudent.get());
        return studentMapper.transformToDTO(oldStudent.get());
    }

    @Override
    public StudentDTO findByAccountNameAndPassword(String accountName, String password) {
        Optional<Student> student = studentRepository.findByAccountNameAndPassword(accountName, password);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the provided account name and password combination is not found.");
        }
        return studentMapper.transformToDTO(student.get());
    }
}
