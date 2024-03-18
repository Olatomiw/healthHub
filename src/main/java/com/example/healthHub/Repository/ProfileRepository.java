package com.example.healthHub.Repository;

import com.example.healthHub.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile>findByStaffId(String staffId);
    Optional<Profile>findByStaffIdOrFirstName(String staffId, String firstName);
    Optional<Profile> findByPhoneNumber(Long number);
}
