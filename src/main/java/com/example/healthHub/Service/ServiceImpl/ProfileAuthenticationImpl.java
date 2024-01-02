package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Dto.Admin.AuthenticationDto;
import com.example.healthHub.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public class ProfileAuthenticationImpl implements AuthenticationService {
    @Override
    public ResponseEntity<?> login(AuthenticationDto authenticationDto, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        return null;
    }
}
