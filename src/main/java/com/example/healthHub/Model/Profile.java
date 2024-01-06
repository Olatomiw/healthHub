package com.example.healthHub.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;

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
    @Column(nullable = false)
    private Long Age;
    @Column(nullable = false)
    private String sex;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long phoneNumber;
    @Column(nullable = false)
    private String Token;
    @Column(nullable = false)
    private String Role;
    @Column(nullable = false)
    private String nextOfKinDetails;
    private String staffId;
    private boolean deleted;
    private String password;

}
