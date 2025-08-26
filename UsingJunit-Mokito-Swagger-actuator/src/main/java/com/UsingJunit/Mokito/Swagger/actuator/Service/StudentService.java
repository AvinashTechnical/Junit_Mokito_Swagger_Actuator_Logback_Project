package com.UsingJunit.Mokito.Swagger.actuator.Service;

import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import com.UsingJunit.Mokito.Swagger.actuator.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create student service
    public Student createStudent(Student student) {
        log.info("Saving new student: {}", student);
        Student savedStudent = studentRepository.save(student);
        log.debug("Student saved: {}", savedStudent);
        return savedStudent;
    }

    public List<Student> getAllStudent() {
        log.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        log.debug("Total students fetched: {}", students.size());
        return students;
    }

    // Find student by roll number
    public Optional<Student> getStudentByRollNumber(Long rollNumber) {
        log.info("Fetching student with roll number: {}", rollNumber);
        Optional<Student> student = studentRepository.findById(rollNumber);
        if (student.isPresent()) {
            log.debug("Student found: {}", student.get());
        } else {
            log.warn("No student found with roll number: {}", rollNumber);
        }
        return student;
    }

    // Update student by roll number
    public Student updateStudent(Long rollNumber, Student updatedStudent) {
        log.info("Updating student with roll number: {}", rollNumber);
        return studentRepository.findById(rollNumber).map(existing -> {
            log.debug("Existing student: {}", existing);
            existing.setName(updatedStudent.getName());
            existing.setDob(updatedStudent.getDob());
            existing.setCity(updatedStudent.getCity());
            existing.setDistrict(updatedStudent.getDistrict());
            existing.setSection(updatedStudent.getSection());
            existing.setMarks(updatedStudent.getMarks());
            Student saved = studentRepository.save(existing);
            log.debug("Updated student: {}", saved);
            return saved;
        }).orElseThrow(() -> {
            log.error("Student with roll number {} not found for update", rollNumber);
            return new RuntimeException("Student not found");
        });
    }

    public void deleteStudent(Long rollNumber) {
        log.info("Deleting student with roll number: {}", rollNumber);
        studentRepository.deleteById(rollNumber);
        log.debug("Student with roll number {} deleted", rollNumber);
    }
}
