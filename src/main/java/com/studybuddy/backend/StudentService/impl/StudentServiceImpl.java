//package com.studybuddy.backend.StudentService.impl;
//
//import com.studybuddy.backend.StudentService.StudentService;
//import com.studybuddy.backend.entity.Student;
//import com.studybuddy.backend.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class StudentServiceImpl implements StudentService {
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Override
//    public Student saveStudent(Student student) {
//        return studentRepository.save(student);
//    }
//
//    @Override
//    public List<Student> getAllStudents() {
//        return studentRepository.findAll();
//    }
//
//    public boolean checkLogin(String email, String password) {
//        // Implement the login check logic here
//        // For example, check if the provided email and password match with the database records
//        // You can use the studentRepository to query the database and verify the login credentials
//
//        Student student = studentRepository.findByEmail(email);
//        if (student != null && student.getPassword().equals(password)) {
//            // Login is successful
//            return true;
//        } else {
//            // Login failed
//            return false;
//        }
//    }
//}
