package com.example.healthHub.Service;

import com.example.healthHub.Config.UserRole;
import com.example.healthHub.Dto.ProfileDto;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    ResponseEntity<?>createProfile(ProfileDto profileDto, UserRole role);
}
