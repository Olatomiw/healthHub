package com.example.healthHub.Service;


import com.example.healthHub.Model.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ExtendedSecurityService {
    void login(Admin admin, HttpServletResponse response, HttpServletRequest request);
    void logout(HttpServletRequest request, HttpServletResponse response);

}
