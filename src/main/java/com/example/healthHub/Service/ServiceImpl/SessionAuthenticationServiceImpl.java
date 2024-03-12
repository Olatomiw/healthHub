package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Model.Admin;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Service.SessionAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationServiceImpl implements SessionAuthenticationService {
    @Override
    public void login(Profile profile, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("login", profile.getStaffId());
    }

    @Override
    public void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null){
            throw new RuntimeException();
        }
        session.invalidate();
    }
}
