package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Model.Admin;
import com.example.healthHub.Repository.AdminRepository;
import com.example.healthHub.Service.CookieAuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;


@Service

public class CookieAuthenticationServiceImpl implements CookieAuthenticationService {
    @Value("${App.cookie.login}")
    private String loginCookieName;
    @Autowired
    private AdminRepository adminRepository;


    @Override
    public void login(Admin admin, HttpServletResponse response) {
        Cookie cookie = new Cookie(loginCookieName, admin.getEmail());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1*60*10);
        response.addCookie(cookie);
    }

    @Override
    public void logOut(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i=0; i<cookies.length; i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(loginCookieName)){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }

    }

    @Override
    public Admin getLoggedInUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean loggedIn = false;
        Cookie loginCookie = null;
        if (cookies == null){
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(loginCookieName)){
                loggedIn = true;
                loginCookie = cookie;
                break;
            }
        }
        if (!loggedIn){
            return null;
        }
        Optional<Admin> admin = adminRepository.findByEmail(loginCookie.getValue());
        if (admin.isEmpty()){
            return null;
        }


        return admin.get();
    }
}