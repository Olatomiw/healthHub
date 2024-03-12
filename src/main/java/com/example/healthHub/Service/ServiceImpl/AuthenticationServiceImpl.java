package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Dto.Admin.AdminDto;
import com.example.healthHub.Dto.Admin.AuthenticationDto;
import com.example.healthHub.Dto.response.ApiResponse;
import com.example.healthHub.Model.Admin;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Repository.AdminRepository;
import com.example.healthHub.Repository.ProfileRepository;
import com.example.healthHub.Service.AuthenticationService;
import com.example.healthHub.Service.CookieAuthenticationService;
import com.example.healthHub.Service.ExtendedSecurityService;
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
    private ProfileRepository profileRepository;
    @Autowired
    private CookieAuthenticationService cookieAuthenticationService;
    @Autowired
    private ExtendedSecurityService securityService;

    @Override
    public ResponseEntity<?> login(AuthenticationDto authenticationDto, HttpServletRequest request, HttpServletResponse response) {
//        Optional<Admin> optionalAdmin = adminRepository.findByEmail(authenticationDto.getEmail());
        Optional<Profile>optionalProfile = profileRepository.findByStaffId(authenticationDto.getEmail());
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            if (optionalProfile.isEmpty()){
                return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
            }
//            if (optionalAdmin.isPresent()){
//                Admin admin = optionalAdmin.get();
//                if (!admin.getPassword().equals(authenticationDto.getPassword())){
//                    return new ResponseEntity<>("Password does not match", HttpStatus.UNAUTHORIZED);
//                }
//                cookieAuthenticationService.login(admin, response );
//                return new ResponseEntity<>("succesful", HttpStatus.OK);
//            }
            if (optionalProfile.isPresent()){
                Profile profile = optionalProfile.get();
                if (!profile.getPassword().equals(authenticationDto.getPassword())){
                    return new ResponseEntity<>("Invalid Login details", HttpStatus.UNAUTHORIZED);
                }
                securityService.login(profile, response, request);
                apiResponse.setMessage("Success");
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
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
