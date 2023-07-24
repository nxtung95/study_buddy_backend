    package com.studybuddy.backend.controller;

    import com.studybuddy.backend.StudentService.StudentService;
    import com.studybuddy.backend.model.Student;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/student")
    @CrossOrigin
    public class StudentController {
        @Autowired
        private StudentService studentService;

        @PostMapping("/add")
        public String add(@RequestBody Student student) {
            studentService.saveStudent(student);
            return "Student has been added";
        }
        @GetMapping("/getAll")
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }
    }
