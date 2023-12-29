package com.example.healthHub.Service;

import com.example.healthHub.Model.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface CookieAuthenticationService {

    void login(Admin  admin, HttpServletResponse response);
    void logOut(HttpServletResponse response, HttpServletRequest request);
    Admin getLoggedInUser(HttpServletRequest request);
}
