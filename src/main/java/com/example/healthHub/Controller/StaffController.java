package com.example.healthHub.Controller;

import com.example.healthHub.Dto.ProfileDto;
import com.example.healthHub.Service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StaffController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(@Valid @RequestBody ProfileDto profileDto){
        return profileService.createProfile(profileDto);
    }
}
