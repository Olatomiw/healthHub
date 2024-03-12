package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Model.Admin;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Service.CookieAuthenticationService;
import com.example.healthHub.Service.ExtendedSecurityService;
import com.example.healthHub.Service.SessionAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExtendedSecurityServiceImpl implements ExtendedSecurityService {


    @Value("${app.authType}")
    private String authType;

    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;
    @Autowired
    private SessionAuthenticationService sessionAuthenticationService;

    @Override
    public void login(Profile profile, HttpServletResponse response, HttpServletRequest request) {
        if (authType.equals("cookie")){
             cookieAuthenticationService.login(profile,response);
        }
        else if (authType.equals("session")){
            sessionAuthenticationService.login(profile, request);
        }
        else{
            System.out.println("<<<<<<<<<<<<<<<<<<<No login Mechanism>>>>>>>>>>>>>>>>>");
        }

    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        return;
    }
}
