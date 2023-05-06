package com.arjunshaji.sample.task.service;

import com.arjunshaji.sample.task.entity.Admin;
import com.arjunshaji.sample.task.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public String saveAdmin(Admin admin) {
        Optional<Admin> admin1 = Optional.ofNullable(adminRepository.findByUsername(admin.getUsername()));
        if(admin1.isPresent()){
            return "Admin is already registered";
        } else {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPassword = bcrypt.encode(admin.getPassword());
            admin.setPassword(encryptedPassword);
            adminRepository.save(admin);
            return "Admin registered successful !!";
        }
    }

    public Admin adminLogin(Admin admin) {
        Optional<Admin> admin2 = Optional.ofNullable(adminRepository.findByUsername(admin.getUsername()));
        if(admin2.isPresent()){
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            Admin admin1 = admin2.get();
            if(admin.getUsername().equals(admin1.getUsername())){
                if(bcrypt.matches(admin.getPassword(),admin1.getPassword())){
                    return adminRepository.findByUsername(admin.getUsername());
                }
            }
        }
        return null;
    }
}
