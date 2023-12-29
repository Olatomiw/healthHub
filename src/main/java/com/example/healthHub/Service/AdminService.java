package com.example.healthHub.Service;

import com.example.healthHub.Dto.Admin.AdminDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface AdminService {
    ResponseEntity<?> createAdmin(AdminDto adminDto);
}
