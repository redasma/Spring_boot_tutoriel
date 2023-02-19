package com.practiceSpring.Spring.Tutorial.jpa.repository;

import com.practiceSpring.Spring.Tutorial.jpa.entity.Guardian;
import com.practiceSpring.Spring.Tutorial.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("redouaneasma1@gmail.com")
                .firstName("redouane")
                .lastName("asma")
                //.guardianEmail("rabah@gmail.com")
                //.guardianMobile("055891589")
               // .guardianName("rabah")
                .build();
        repository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("rabah")
                .email("redouaneasma1@gmail.com")
                .mobile("5555555555")
                .build();

        Student student = Student.builder()
                .firstName("amine")
                .lastName("asma")
                .emailId("amineasma")
                .guardian(guardian).build();
        repository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = repository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList= repository.findByFirstName("amine");
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList= repository.findByFirstNameContaining("e");
        System.out.println(studentList);
    }

}