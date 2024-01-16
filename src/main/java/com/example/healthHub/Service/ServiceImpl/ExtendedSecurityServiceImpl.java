package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Model.Admin;
import com.example.healthHub.Service.CookieAuthenticationService;
import com.example.healthHub.Service.ExtendedSecurityService;
import com.example.healthHub.Service.SessionAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ExtendedSecurityServiceImpl implements ExtendedSecurityService {


    @Value("${app.AuthTYpe}")
    private String authTYpe;

    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;

    @Autowired
    private SessionAuthenticationService sessionAuthenticationService;

    @Override
    public void login(Admin admin, HttpServletResponse response, HttpServletRequest request) {
        if (authTYpe.equals("cookie")){
             cookieAuthenticationService.login(admin,response);
        }
        else if (authTYpe.equals("session")){
            sessionAuthenticationService.login(admin, request);
        }

    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        

    }
}
