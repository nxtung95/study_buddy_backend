package com.studybuddy.backend.StudentService;

import com.studybuddy.backend.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    boolean checkLogin(String email, String password);
}
