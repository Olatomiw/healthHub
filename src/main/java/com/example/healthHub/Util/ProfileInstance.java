package com.example.healthHub.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileInstance {
    private String firstName;
    private String lastName;
    private String sex;
    private String staffId;
    private Enum role;
    private Long age;
}
