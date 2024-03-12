package com.example.healthHub.Service;

import com.example.healthHub.Model.Admin;
import com.example.healthHub.Model.Profile;
import jakarta.servlet.http.HttpServletRequest;

public interface SessionAuthenticationService {
    void login (Profile profile, HttpServletRequest request);
    void logOut(HttpServletRequest request);
}
