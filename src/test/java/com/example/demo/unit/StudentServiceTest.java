package com.example.demo.unit;

import com.example.demo.dto.StudentDTO;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.models.Student;
import com.example.demo.repos.StudentRepository;
import com.example.demo.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    private StudentServiceImpl service;
    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    public void init() {
        service = new StudentServiceImpl(studentRepository, studentMapper);
        student = new Student(1, "John", "johh", "pas1");
        studentDTO = new StudentDTO(1, "John", "johh", "pas1");
        /*Mockito.when(studentMapper.transformToDTO(Mockito.any(Student.class))).thenReturn(Mockito.mock(StudentDTO.class));
        Mockito.when(studentMapper.transformToEntity(Mockito.any(StudentDTO.class))).thenReturn(Mockito.mock(Student.class));*/
        Mockito.when(studentMapper.transformToDTO(Mockito.any(Student.class))).thenReturn(studentDTO);
        Mockito.when(studentMapper.transformToEntity(Mockito.any(StudentDTO.class))).thenReturn(student);
    } // alternative is to @Inject on dependencies

    @Test
    //@Disabled("Not so much point, just for presentation")
    public void saveTest() {
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(student);
        StudentDTO savedStudent = service.save(studentDTO);
        Assertions.assertEquals(1, savedStudent.getId());
    }

    @Test
    public void findOneTest() {
        Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> service.findOne(1));
        Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        StudentDTO studentDTO = service.findOne(1);
        Assertions.assertEquals("John", studentDTO.getName());
    }

    @Test
    public void updateTest() {
        Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> service.update(1, studentDTO));
        Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        StudentDTO dto = service.update(1, studentDTO);
        Assertions.assertEquals("John", dto.getName());
    }


}
