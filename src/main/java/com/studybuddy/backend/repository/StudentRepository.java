package com.studybuddy.backend.repository;

import com.studybuddy.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);

//    Student findById(String id);
//    Student findBySubject(String subject);
}
