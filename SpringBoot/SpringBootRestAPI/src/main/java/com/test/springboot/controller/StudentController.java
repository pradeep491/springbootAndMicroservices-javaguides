package com.test.springboot.controller;

import com.test.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    //http://localhost:8080/student
    @GetMapping("/student")
    public Student getStudent() {
        Student student = new Student(491, "pradeep", "kumar");
        return student;
    }

    //http://localhost:8080/students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(491, "pradeep", "kandyala"));
        students.add(new Student(492, "punnu", "kandyala"));
        students.add(new Student(493, "jasvin", "kandyala"));
        students.add(new Student(494, "jyotshna", "kandyala"));
        return students;
    }

    //Spring boot with Rest Path Variable
    //http://localhost:8080/student/491
    //{id} - URI template variable
    @GetMapping("/student/{id}")
    public Student studentPathVariable(@PathVariable int id) {
       /* List<Student> students = new ArrayList<>();
        students.add(new Student(491, "pradeep", "kandyala"));
        students.add(new Student(492, "punnu", "kandyala"));
        students.add(new Student(493, "jasvin", "kandyala"));
        students.add(new Student(494, "jyotshna", "kandyala"));
        Student student = null;
        for (Student s : students) {
            if (s.getId() == id) {
                student = new Student(s.getId(), s.getFirstName(), s.getLastName());
            }
        }
        return student;*/
        return new Student(id, "pradeep", "kandyala");
    }

    //Spring boot with Rest Path Variable
    //http://localhost:8080/student/491/pradeep/kandyala
    //{id} - URI template variable
    @GetMapping("/student/{id}/{first-name}/{last-name}")
    public Student studentPathVariables(@PathVariable int id,
                                        @PathVariable("first-name") String fname,
                                        @PathVariable("last-name") String lname) {

        return new Student(id, fname, lname);
    }

    //Spring boot with Rest API with Request Param
    //http://localhost:8080/student?id=491&first-name=pradeep&last-name=kandyala
    //{id} - URI template variable
    @GetMapping("/students/query")
    public Student studentRequestVariables(@RequestParam("id") int id,
                                           @RequestParam("first-name") String fname,
                                           @RequestParam("last-name") String lname) {

        return new Student(id, fname, lname);
    }

    //Post Mapping Student Data-@PostMapping
    //@RequestBody
    //http://localhost:8080/student/create
    @PostMapping("/student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //PUT Request - to update the existing Resource
    //http://localhost:8080/
    @PutMapping("/students/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //HTTP Status DELETE Request-Spring Boot REST API
    //@DeleteMapping
    @DeleteMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "Student deleted successfully!";
    }


}
