package com.UsingJunit.Mokito.Swagger.actuator.Controller;


import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import com.UsingJunit.Mokito.Swagger.actuator.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @GetMapping("/allStudents")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/{rollNumber}")
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Optional<Student> getStudent(@PathVariable Long rollNumber) {
        return studentService.getStudentByRollNumber(rollNumber);
    }

    @PutMapping("/{rollNumber}")
    public Student updateStudent(@PathVariable Long rollNumber, @RequestBody Student student) {
        return studentService.updateStudent(rollNumber, student);
    }

    @DeleteMapping("/{rollNumber}")
    public String deleteStudent(@PathVariable Long rollNumber) {
        studentService.deleteStudent(rollNumber);
        return "Student with roll number " + rollNumber + " deleted.";
    }
}
