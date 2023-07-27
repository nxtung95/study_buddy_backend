    package com.studybuddy.backend.controller;

    import com.studybuddy.backend.StudentService.StudentService;
    import com.studybuddy.backend.dto.ResponseDTO;
    import com.studybuddy.backend.dto.StudentLoginRequest;
    import com.studybuddy.backend.model.Student;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
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
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody StudentLoginRequest loginRequest) {
            boolean loginIsSuccessful = studentService.checkLogin(loginRequest.getEmail(), loginRequest.getPassword());
            if (loginIsSuccessful) {
                // If login is successful, return a success response
                return ResponseEntity.ok().body(new ResponseDTO(true, "Login successful"));
            } else {
                // If login fails, return an error response with an error message
                return ResponseEntity.badRequest().body(new ResponseDTO(false, "Invalid credentials"));
            }
        }
        @GetMapping("/getAll")
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }
    }
