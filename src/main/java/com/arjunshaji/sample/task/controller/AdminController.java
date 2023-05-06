package com.arjunshaji.sample.task.controller;

import com.arjunshaji.sample.task.entity.Admin;
import com.arjunshaji.sample.task.repository.AdminRepository;
import com.arjunshaji.sample.task.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public String saveAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin,HttpSession session){
        Admin admin1 = adminService.adminLogin(admin);
        if(admin1 != null){
            session.setAttribute("username",admin1.getUsername());
            return new ResponseEntity<>("Admin login Successful !!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Admin login Failed",HttpStatus.UNAUTHORIZED);
        }
    }
}
