package com.example.healthHub.Service.ServiceImpl;


import com.example.healthHub.Config.UserRole;
import com.example.healthHub.Dto.ProfileDto;
import com.example.healthHub.Dto.response.ApiResponse;
import com.example.healthHub.Model.Profile;
import com.example.healthHub.Model.Token;
import com.example.healthHub.Repository.ProfileRepository;
import com.example.healthHub.Repository.TokenRepository;
import com.example.healthHub.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public ResponseEntity<?> createProfile(ProfileDto profileDto) {
        Profile profile = new Profile();
        ApiResponse<String> apiResponse = new ApiResponse<>();
        int currentYear = Year.now().getValue();
        Long count = profileRepository.count();
        Long id = ++count;
        try{
            profile.setFirstName(profileDto.getFirstName());
            profile.setLastName(profileDto.getLastName());
            profile.setAge(profileDto.getAge());
            profile.setRole(UserRole.ADMIN);
            profile.setSex(profileDto.getSex());
            profile.setAddress(profileDto.getAddress());
            profile.setEmail(profileDto.getEmail());
            profile.setPhoneNumber(profileDto.getPhoneNumber());
            profile.setStaffId(String.format("KH/%04d/%d", id, currentYear));
            profile.setPassword(profileDto.getPassword());
            String staffId = profile.getStaffId();
            Optional<Profile> optionalProfile=profileRepository.findByStaffId(staffId);
            if(optionalProfile.isPresent()){
                apiResponse.setMessage("User already exist");
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }
            profileRepository.save(profile);
            apiResponse.setData(staffId);
            apiResponse.setMessage("Login with your unique ID");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
