package com.example.healthHub.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProfileDto {
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Long Age;
    @NotBlank
    @NotNull
    private String sex;
    @NotBlank
    @NotNull
    private String address;
    @NotNull
    private Long phoneNumber;
    @NotBlank
    @NotNull
    private String Token;
    @NotBlank
    @NotNull
    private String Role;
    @NotBlank
    @NotNull
    private String nextOfKinDetails;
    @NotBlank
    @NotNull
    private String password;



}
