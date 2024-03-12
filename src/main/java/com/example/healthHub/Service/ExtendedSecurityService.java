package com.example.healthHub.Service;


import com.example.healthHub.Model.Admin;
import com.example.healthHub.Model.Profile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ExtendedSecurityService {
    void login(Profile profile, HttpServletResponse response, HttpServletRequest request);
    void logout(HttpServletRequest request, HttpServletResponse response);

}
