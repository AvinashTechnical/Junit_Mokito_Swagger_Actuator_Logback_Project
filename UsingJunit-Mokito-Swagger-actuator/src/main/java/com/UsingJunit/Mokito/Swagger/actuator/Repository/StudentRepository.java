package com.UsingJunit.Mokito.Swagger.actuator.Repository;

import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
