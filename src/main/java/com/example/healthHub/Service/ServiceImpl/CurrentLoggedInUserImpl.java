package com.example.healthHub.Service.ServiceImpl;

import com.example.healthHub.Model.Profile;
import com.example.healthHub.Repository.ProfileRepository;
import com.example.healthHub.Service.CurrentLoggedInUserService;
import com.example.healthHub.Service.ProfileService;
import com.example.healthHub.Util.ProfileInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CurrentLoggedInUserImpl implements CurrentLoggedInUserService {
    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public ResponseEntity<ProfileInstance> getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal()instanceof UserDetails){
                UserDetails userDetails = (UserDetails)authentication.getPrincipal();
                Object details = authentication.getDetails();
                String username = userDetails.getUsername();
                Optional<Profile> byStaffId = profileRepository.findByStaffId(username);
                ProfileInstance profileInstance = new ProfileInstance();
                Profile profile = byStaffId.get();
//              Making Userdetails returned more secure
                profileInstance.setFirstName(profile.getFirstName());
                profileInstance.setLastName(profile.getLastName());
                profileInstance.setSex(profile.getSex());
                profileInstance.setStaffId(profile.getStaffId());
                profileInstance.setAge(profile.getAge());
                return new ResponseEntity<>(profileInstance, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
