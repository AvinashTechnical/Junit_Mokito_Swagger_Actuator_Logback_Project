package com.UsingJunit.Mokito.Swagger.actuator.Controller;

import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import com.UsingJunit.Mokito.Swagger.actuator.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
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
        log.info("Creating new student: {}", student);
        Student created = studentService.createStudent(student);
        log.debug("Created student: {}", created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/allStudents")
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    })
    public ResponseEntity<List<Student>> getAllStudent() {
        log.info("Fetching all students");
        List<Student> students = studentService.getAllStudent();
        log.debug("Number of students retrieved: {}", students.size());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{rollNumber}")
    @Operation(summary = "Get student by roll number", description = "Returns a single student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> getStudent(@PathVariable Long rollNumber) {
        log.info("Fetching student with roll number: {}", rollNumber);
        Optional<Student> student = studentService.getStudentByRollNumber(rollNumber);
        return student.map(value -> {
            log.debug("Student found: {}", value);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> {
            log.warn("Student not found for roll number: {}", rollNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @PutMapping("/{rollNumber}")
    @Operation(summary = "Update student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> updateStudent(@PathVariable Long rollNumber, @RequestBody Student student) {
        log.info("Updating student with roll number: {}", rollNumber);
        Student updated = studentService.updateStudent(rollNumber, student);
        log.debug("Updated student: {}", updated);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{rollNumber}")
    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<String> deleteStudent(@PathVariable Long rollNumber) {
        log.info("Deleting student with roll number: {}", rollNumber);
        studentService.deleteStudent(rollNumber);
        log.debug("Student with roll number {} deleted", rollNumber);
        return new ResponseEntity<>("Student with roll number " + rollNumber + " deleted.", HttpStatus.OK);
    }
}
