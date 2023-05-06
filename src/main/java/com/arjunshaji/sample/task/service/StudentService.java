package com.arjunshaji.sample.task.service;

import com.arjunshaji.sample.task.entity.Student;
import com.arjunshaji.sample.task.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

//    public Optional<Student> getStudentById(Long id) {
//        return studentRepository.findById(id);
//    }

    public Object getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return studentRepository.findById(id);
        } else {
            return "Student not found in this ID ";
        }
    }

    public String deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
            return "Student deleted successfully !!";
        } else {
            return "Student not found in this ID ";
        }
    }

    public Object UpdateStudentById(Long id, Student student) {
        Optional<Student> student1 = studentRepository.findById(id);
        if (student1.isPresent()) {

            if (Objects.nonNull(student.getName()) && !"".equalsIgnoreCase(student.getName())) {
                student1.get().setName(student.getName());
            }
            if (Objects.nonNull(student.getAge()) && !"".equalsIgnoreCase(student.getAge())) {
                student1.get().setAge(student.getAge());
            }
            if (Objects.nonNull(student.getDepartment()) && !"".equalsIgnoreCase(student.getDepartment())) {
                student1.get().setDepartment(student.getDepartment());
            }
            if (Objects.nonNull(student.getTotalMark()) && !"".equalsIgnoreCase(student.getTotalMark())) {
                student1.get().setTotalMark(student.getTotalMark());
            }

            Student student2 = student1.get();
            return studentRepository.save(student2);

        } else {
            return "Student not found in this ID ";
        }
    }
}

//    public void UpdateStudentById(Long id, Student student) {
//        Student student1 = studentRepository.findById(id).get();
//        if(Objects.nonNull(student.getName()) && !"".equalsIgnoreCase(student.getName())){
//            student1.setName(student.getName());
//        }
//        if(Objects.nonNull(student.getAge()) && !"".equalsIgnoreCase(student.getAge())){
//            student1.setAge(student.getAge());
//        }
//        if(Objects.nonNull(student.getDepartment()) && !"".equalsIgnoreCase(student.getDepartment())){
//            student1.setDepartment(student.getDepartment());
//        }
//        if(Objects.nonNull(student.getTotalMark()) && !"".equalsIgnoreCase(student.getTotalMark())){
//            student1.setTotalMark(student.getTotalMark());
//        }
//        studentRepository.save(student1);
//    }
