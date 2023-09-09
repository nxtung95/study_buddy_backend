package com.studybuddy.backend.repository;

import com.studybuddy.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
//    Subject findByEmail(String email);
}