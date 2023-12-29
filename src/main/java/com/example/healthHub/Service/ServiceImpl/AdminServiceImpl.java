package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Dto.Admin.AdminDto;
import com.example.healthHub.Model.Admin;
import com.example.healthHub.Repository.AdminRepository;
import com.example.healthHub.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public ResponseEntity<?> createAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        try{
            Optional<Admin> optionalAdmin = adminRepository.findByEmail(adminDto.getEmail());
            if(optionalAdmin.isPresent()){
                return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
            }
            admin.setEmail(adminDto.getEmail());
            admin.setPassword(adminDto.getPassword());
            admin.setFullName(adminDto.getFullName());

            adminRepository.save(admin);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed",  HttpStatus.BAD_REQUEST);
    }
}
