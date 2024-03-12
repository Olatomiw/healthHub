package com.example.healthHub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;


import java.util.Collection;

@Entity
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Long Age;
    @Column(nullable = false)
    private String sex;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long phoneNumber;
    @Column(nullable = false)
    private String Role;
    private String staffId;
    private boolean deleted;
    @Column(nullable = false)
    @Size(min = 8)
    private String password;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
