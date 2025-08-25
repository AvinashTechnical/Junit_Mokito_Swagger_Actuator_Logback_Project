package com.UsingJunit.Mokito.Swagger.actuator.Enitiy;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Student {


    public Long getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Long rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }


    public Student(Long rollNumber, String name, LocalDate dob, String city, String district, String section, Double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.dob = dob;
        this.city = city;
        this.district = district;
        this.section = section;
        this.marks = marks;
    }


    @Override
    public String toString() {
        return "Student{" +
                "rollNumber=" + rollNumber +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", section='" + section + '\'' +
                ", marks=" + marks +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollNumber;
    private String name;
    private LocalDate dob;

    private String city;
    private String district;
    private String section;
    private Double marks;
}
