package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Model.Admin;
import com.example.healthHub.Service.SessionAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationServiceImpl implements SessionAuthenticationService {
    @Override
    public void login(Admin admin, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("login", admin.getEmail());
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
