package com.example.healthHub.Controller;

import com.example.healthHub.Config.UserRole;
import com.example.healthHub.Dto.ProfileDto;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Repository.ProfileRepository;
import com.example.healthHub.Service.CurrentLoggedInUserService;
import com.example.healthHub.Service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class StaffController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CurrentLoggedInUserService currentLoggedInUserService;

    @PostMapping("/register/doctor")
    public ResponseEntity<?> createProfile(@Valid @RequestBody ProfileDto profileDto){
        return profileService.createProfile(profileDto, UserRole.ROLE_DOCTOR);
    }
    @PostMapping("/register/nurse")
    public ResponseEntity<?>nurseRegistration(@Valid @RequestBody ProfileDto profileDto){
        return profileService.createProfile(profileDto, UserRole.ROLE_NURSE);
    }

    @PostMapping("/register/lab")
    public ResponseEntity<?>labRegistration(@Valid @RequestBody ProfileDto profileDto){
        return profileService.createProfile(profileDto,UserRole.ROLE_LAB_ATTENDANT);
    }


    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(){
        return new ResponseEntity<>(currentLoggedInUserService, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> fetchUser(@PathVariable Long id){
        Optional<Profile> byId = profileRepository.findById(id);
        try {
            if (byId.isEmpty()){
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
            if (byId.isPresent()){
                return new ResponseEntity<>(byId.get(), HttpStatus.OK);
            }
        }
        catch (Exception e){e.printStackTrace();}

        return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
    }
}
