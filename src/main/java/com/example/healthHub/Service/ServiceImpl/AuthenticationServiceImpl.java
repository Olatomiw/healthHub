package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Dto.Admin.AdminDto;
import com.example.healthHub.Dto.Admin.AuthenticationDto;
import com.example.healthHub.Model.Admin;
import com.example.healthHub.Repository.AdminRepository;
import com.example.healthHub.Service.AuthenticationService;
import com.example.healthHub.Service.CookieAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;

    @Override
    public ResponseEntity<?> login(AuthenticationDto authenticationDto, HttpServletRequest request, HttpServletResponse response) {
        try {
            Optional<Admin> optionalAdmin = adminRepository.findByEmail(authenticationDto.getEmail());
            if (optionalAdmin.isEmpty()){
                return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
            }
            Admin admin = optionalAdmin.get();
            if (!admin.getPassword().equals(authenticationDto.getPassword())){
                return new ResponseEntity<>("Password does not match", HttpStatus.UNAUTHORIZED);
            }
            cookieAuthenticationService.login(admin, response );

            return new ResponseEntity<>("succesful", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        cookieAuthenticationService.getLoggedInUser(request);
        return null;
    }
}
