package com.cravemate.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoUpdateDTO {
    private String phonenumber;
    private String secretquestion;
    private String answer;
    private String newpassword;

    // Getters and setters
}
