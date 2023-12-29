package com.example.healthHub.Service.ServiceImpl;


import com.example.healthHub.Dto.ProfileDto;
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
            profile.setStaffId(String.format("KH/%%%d/%d", randomNum, currentYear));
            String staffId = profile.getStaffId();
            Optional<Profile> optionalProfile=profileRepository.findByStaffIdOrFirstName(staffId, profileDto.getFirstName());
            if(optionalProfile.isPresent()){
                return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
            }
            Optional<Token> optionalToken = tokenRepository.findByToken(profileDto.getToken());
            if (optionalToken.isEmpty()){
                return new ResponseEntity<>("Token doesn't exist", HttpStatus.NOT_FOUND);
            }
            Token token = optionalToken.get();
            if (token.isUsed() == true){
                return new ResponseEntity<>("Token already used", HttpStatus.BAD_REQUEST);
            }
            Date expChecker = Date.from(Instant.now());
            if(expChecker.after(token.getExpiresAt())){
                token.setUsed(true);
                tokenRepository.save(token);
                return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
            }
            token.setUsed(true);
            tokenRepository.save(token);
            profileRepository.save(profile);
            return new ResponseEntity<>(staffId, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
