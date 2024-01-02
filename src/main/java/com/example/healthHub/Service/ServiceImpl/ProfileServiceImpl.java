package com.example.healthHub.Service.ServiceImpl;


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
        Random random = new Random();
        ApiResponse<String> apiResponse = new ApiResponse<>();
        int randomNum = random.nextInt(1000)+0001;
        int currentYear = Year.now().getValue();
        try{
            profile.setFirstName(profileDto.getFirstName());
            profile.setLastName(profileDto.getLastName());
            profile.setAge(profileDto.getAge());
            profile.setRole(profileDto.getRole());
            profile.setSex(profileDto.getSex());
            profile.setAddress(profileDto.getAddress());
            profile.setToken(profileDto.getToken());
            profile.setNextOfKinDetails(profileDto.getNextOfKinDetails());
            profile.setPhoneNumber(profileDto.getPhoneNumber());
            profile.setPassword(profileDto.getPassword());
            profile.setStaffId(String.format("KH/%04d/%d", randomNum, currentYear));
            String staffId = profile.getStaffId();
            Optional<Profile> optionalProfile=profileRepository.findByStaffIdOrFirstName(staffId, profileDto.getFirstName());
            if(optionalProfile.isPresent()){
                apiResponse.setMessage("User already exist");
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }
            Optional<Token> optionalToken = tokenRepository.findByToken(profileDto.getToken());
            if (optionalToken.isEmpty()){
                apiResponse.setMessage("Token doesn't exist");
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
            Token token = optionalToken.get();
            if (token.isUsed() == true){
                apiResponse.setMessage("token already used");
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }
            Instant expChecker = Instant.now();
            if(expChecker.isAfter(token.getExpiresAt())){
                tokenRepository.delete(token);
                apiResponse.setMessage("Invalid Token");
                return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            token.setUsed(true);
            tokenRepository.save(token);
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
