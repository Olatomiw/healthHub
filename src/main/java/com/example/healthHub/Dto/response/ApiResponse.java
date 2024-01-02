package com.example.healthHub.Dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <G>{
    private String message;
    private G data;
}
