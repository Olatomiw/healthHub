package com.example.healthHub.Repository;

import com.example.healthHub.Model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Profile> profile = profileRepository.findByStaffId(username);

        return profile.map(user ->
                new User(user.getStaffId(), user.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRole().toString()))))
                .orElseThrow(()->new UsernameNotFoundException("Username not found" + username));
    }
}
