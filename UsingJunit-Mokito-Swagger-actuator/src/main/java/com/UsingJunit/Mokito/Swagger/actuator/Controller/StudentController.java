package com.UsingJunit.Mokito.Swagger.actuator.Controller;

import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import com.UsingJunit.Mokito.Swagger.actuator.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Create new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/allStudents")
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    })
    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);
    }

    @GetMapping("/{rollNumber}")
    @Operation(summary = "Get student by roll number", description = "Returns a single student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> getStudent(@PathVariable Long rollNumber) {
        Optional<Student> student = studentService.getStudentByRollNumber(rollNumber);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{rollNumber}")
    @Operation(summary = "Update student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> updateStudent(@PathVariable Long rollNumber, @RequestBody Student student) {
        Student updated = studentService.updateStudent(rollNumber, student);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{rollNumber}")
    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<String> deleteStudent(@PathVariable Long rollNumber) {
        studentService.deleteStudent(rollNumber);
        return new ResponseEntity<>("Student with roll number " + rollNumber + " deleted.", HttpStatus.OK);
    }
}
