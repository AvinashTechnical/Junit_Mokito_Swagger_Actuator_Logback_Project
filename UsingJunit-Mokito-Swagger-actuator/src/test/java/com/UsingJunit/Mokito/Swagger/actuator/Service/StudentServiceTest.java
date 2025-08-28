package com.UsingJunit.Mokito.Swagger.actuator.Service;

import com.UsingJunit.Mokito.Swagger.actuator.Enitiy.Student;
import com.UsingJunit.Mokito.Swagger.actuator.Repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockitoBean
    private StudentRepository studentRepository;

    @Test
    @Disabled
    public void testAdd(){
        assertEquals(4,2+1);
       Assertions.assertNotNull(studentRepository.findById(1L));

    }
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4"
    })
    public void test(int a,int b,int expect){
        assertEquals(expect,a+b);
    }






}
