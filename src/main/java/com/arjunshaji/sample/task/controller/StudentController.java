package com.arjunshaji.sample.task.controller;

import com.arjunshaji.sample.task.entity.Student;
import com.arjunshaji.sample.task.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public String addStudent(@RequestBody Student student, HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null){
            return "Admin not found ?? Admin login required !!";
        } else {
            studentService.addStudent(student);
            return "Student added successful !!";
        }
    }


    @GetMapping("/{id}")
    public Object getStudentById(@PathVariable Long id,HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null){
            return "Admin not found ?? Admin login required !!";
        } else {
            return studentService.getStudentById(id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable Long id,HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null){
            return "Admin not found ?? Admin login required !!";
        } else {
            return studentService.deleteStudentById(id);
        }
    }

    @PutMapping("/update/{id}")
    public Object UpdateStudentById(@PathVariable Long id,@RequestBody Student student,HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null){
            return "Admin not found ?? Admin login required !!";
        } else {
            return studentService.UpdateStudentById(id,student);
            //return "Student Updated Successful !!";
        }
    }
}
