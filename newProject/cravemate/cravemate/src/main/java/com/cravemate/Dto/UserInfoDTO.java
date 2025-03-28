package com.cravemate.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserInfoDTO {
    private String name;
    private String phonenumber;
    private String secretquestion;
    private String address;
    private String answer;
    private String password; // Do not return password to client in user info

    // Getters and setters
}
