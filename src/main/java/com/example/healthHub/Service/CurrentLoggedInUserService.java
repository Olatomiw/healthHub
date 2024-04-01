package com.example.healthHub.Service;

import com.example.healthHub.Model.Profile;
import com.example.healthHub.Util.ProfileInstance;
import org.springframework.http.ResponseEntity;

public interface CurrentLoggedInUserService {

    public ResponseEntity<ProfileInstance> getCurrentLoggedInUser();

}
