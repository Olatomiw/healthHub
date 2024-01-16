package com.example.healthHub.Service;

import com.example.healthHub.Model.Admin;
import jakarta.servlet.http.HttpServletRequest;

public interface SessionAuthenticationService {
    void login (Admin admin, HttpServletRequest request);
    void logOut(HttpServletRequest request);
}
