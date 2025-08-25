package com.UsingJunit.Mokito.Swagger.actuator.Service;


import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import com.UsingJunit.Mokito.Swagger.actuator.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

     @Autowired
    private StudentRepository studentRepository;
// create student service..........................................
     public Student createStudent(Student student){
 return studentRepository.save(student);
     }

     public List<Student> getAllStudent(){
         return studentRepository.findAll();
     }

     // find by student by roll number.........................
     public Optional<Student> getStudentByRollNumber(Long rollNumber){
         return studentRepository.findById(rollNumber);
     }

     // update student by roll number.................................

    public Student updateStudent(Long rollNumber, Student updatedStudent) {
        return studentRepository.findById(rollNumber).map(existing -> {
            existing.setName(updatedStudent.getName());
            existing.setDob(updatedStudent.getDob());
            existing.setCity(updatedStudent.getCity());
            existing.setDistrict(updatedStudent.getDistrict());
            existing.setSection(updatedStudent.getSection());
            existing.setMarks(updatedStudent.getMarks());
            return studentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long rollNumber) {
        studentRepository.deleteById(rollNumber);
    }

}
