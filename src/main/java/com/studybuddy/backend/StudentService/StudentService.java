package com.studybuddy.backend.StudentService;

import com.studybuddy.backend.model.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
}
