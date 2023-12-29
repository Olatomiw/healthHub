package com.example.healthHub.Service;

import com.example.healthHub.Dto.Admin.AdminDto;
import com.example.healthHub.Dto.Admin.AuthenticationDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<?>login(AuthenticationDto authenticationDto, HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<?> logOut(HttpServletRequest request );

}
