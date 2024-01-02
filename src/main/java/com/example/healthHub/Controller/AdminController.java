package com.example.healthHub.Controller;

import com.example.healthHub.Dto.Admin.AdminDto;
import com.example.healthHub.Dto.Admin.AuthenticationDto;
import com.example.healthHub.Dto.Admin.TokenDto;
import com.example.healthHub.Dto.response.ApiResponse;
import com.example.healthHub.Model.Admin;
import com.example.healthHub.Model.Token;
import com.example.healthHub.Repository.AdminRepository;
import com.example.healthHub.Repository.TokenRepository;
import com.example.healthHub.Service.AdminService;
import com.example.healthHub.Service.AuthenticationService;
import com.example.healthHub.Service.CookieAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;


    @PostMapping("/create/admin")
    public ResponseEntity<?> createToken(@Valid @RequestBody AdminDto adminDto){
        return  adminService.createAdmin(adminDto);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDto authenticationDto, HttpServletResponse response, HttpServletRequest request){
       return authenticationService.login(authenticationDto,request, response );
    }
    @PostMapping("/token")
    public ResponseEntity<?> createToken(@Valid @RequestBody TokenDto tokenDto, HttpServletRequest request){
       Admin admin = cookieAuthenticationService.getLoggedInUser(request);
        List<Token> tokens = admin.getTokens();
        Token generatedToken = new Token();
        ApiResponse<String> objectApiResponse = new ApiResponse<>();
        generatedToken.setGeneratedFor(tokenDto.getGeneratedFor());
        generatedToken.setToken(UUID.randomUUID().toString());
        generatedToken.setCreatedAt(Instant.now());
        Instant createdAt = generatedToken.getCreatedAt();
        generatedToken.setExpiresAt(createdAt.plus(1, ChronoUnit.HOURS));
        tokens.add(generatedToken);
        String token = generatedToken.getToken();
        objectApiResponse.setData(token);
        objectApiResponse.setMessage("Successfully Created");
        adminRepository.save(admin);
        return new ResponseEntity<>(objectApiResponse,  HttpStatus.CREATED);
    }

}
