package com.test.springboot.controller;

import com.test.springboot.bean.Student;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    //http://localhost:8080/student
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(491, "pradeep", "kumar");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custome-header", "pradeep")
                .body(student);
    }

    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(491, "pradeep", "kandyala"));
        students.add(new Student(492, "punnu", "kandyala"));
        students.add(new Student(493, "jasvin", "kandyala"));
        students.add(new Student(494, "jyotshna", "kandyala"));
        //return students;
        return ResponseEntity.ok(students);
    }

    //Spring boot with Rest Path Variable
    //http://localhost:8080/student/491
    //{id} - URI template variable
    @GetMapping("/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id) {
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
        Student student = new Student(id, "pradeep", "kandyala");
        return ResponseEntity.ok(student);
    }

    //Spring boot with Rest Path Variable
    //http://localhost:8080/student/491/pradeep/kandyala
    //{id} - URI template variable
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariables(@PathVariable int id,
                                                        @PathVariable("first-name") String fname,
                                                        @PathVariable("last-name") String lname) {

        Student student = new Student(id, fname, lname);
        return ResponseEntity.ok(student);
    }

    //Spring boot with Rest API with Request Param
    //http://localhost:8080/student?id=491&first-name=pradeep&last-name=kandyala
    //{id} - URI template variable
    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariables(@RequestParam("id") int id,
                                                           @RequestParam("first-name") String fname,
                                                           @RequestParam("last-name") String lname) {

        Student student = new Student(id, fname, lname);
        return ResponseEntity.ok(student);
    }

    //Post Mapping Student Data-@PostMapping
    //@RequestBody
    //http://localhost:8080/student/create
    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //PUT Request - to update the existing Resource
    //http://localhost:8080/
    @PutMapping("/{id}/update")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;
        return ResponseEntity.ok(student);
    }

    //HTTP Status DELETE Request-Spring Boot REST API
    //@DeleteMapping
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        System.out.println(id);
        //return "Student deleted successfully!";
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
