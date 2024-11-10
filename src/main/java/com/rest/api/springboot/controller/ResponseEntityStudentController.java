package com.rest.api.springboot.controller;
import com.rest.api.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ResponseEntityStudentController {

    //Spring Boot REST API that returns java beans as JSON
    // http://localhost:8080/student
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                " Shreya ",
                " Yadav "    );
       // return new ResponseEntity<>(student, HttpStatus.OK );
        return ResponseEntity.ok(student);
       // return ResponseEntity.ok().header("custom-header" , "shreya").body(student);
    }

    //Spring Boot REST API that returns list in JSON format
    // http://localhost:8080/students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1 , "Shreya ", "Yadav "));
        students.add(new Student(2 , "Riya ", "Yadav "));
        students.add(new Student(3 , "Ram ", "singh "));
        return ResponseEntity.ok(students);
    }

    // Spring Boot REST API with Path Variable
    // http://localhost:8080/students/1
    // {id} - URI template variable
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id){
        Student student = new Student(id , "Shreya ", "Yadav ");
        return ResponseEntity.ok().body(student);
    }


    // Spring Boot REST API with Path Variable,
    // when the URI variable and method parameter have different names.
    // http://localhost:8080/students-second/3
    @GetMapping("/students-second/{id}")
    public Student studentPathVariable1(@PathVariable("id") int studentId){
        return new Student(studentId,  "Riya" , "Yadav");
    }

    // Spring Boot REST API with Path Variable, When we pass multiple path variables in a URL
    // http://localhost:8080/students-second/4/shreya/yadav
    @GetMapping("/students-second/{id}/{first-name}/{last-name}")
    public Student studentPathVariable2(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName)
    {
        return new Student(studentId,  firstName , lastName);
    }

    // Spring Boot REST API with Request Param
    // http://localhost:8080/students/query?id=1
    @GetMapping("/students/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id){
       Student student = new Student(id , "Shreya ", "Yadav");
       return ResponseEntity.ok().body(student);
    }

    // Spring Boot REST API with Request Param, when we have multiple query parameter in a request
    // http://localhost:8080/students/query?id=1&firstname=Shreya&lastName=Yadav
    @GetMapping("/student/query")
    public Student studentRequestVariable1(@RequestParam int id ,
                                           @RequestParam String firstName ,
                                           @RequestParam String lastName)
    {
        return new Student(id , firstName, lastName);
    }

    // Spring Boot REST API that handles HTTP POST Request - creating new resources
    // @PostMapping and @RequestBody
    // http://localhost:8080/student/create
    @PostMapping("/student/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring Boot REST API that handles HTTP PUT Request - updating existing resource
    // http://localhost:8080/student/5/update
    @PutMapping("/student/{id}/update")
    public ResponseEntity<Student> updatestudent(@RequestBody Student student ,
                                                 @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting the existing resource
    // http://localhost:8080/student/4/delete
    @DeleteMapping("student/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Deleted successfully !");
    }

}
