package com.user.users.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {
    @NotBlank(message = "email should not blank")
    public String eMail;
    @NotBlank(message = "password should not empty")
    @Size(min = 6, max = 15, message = "please enter the min 6 character and max 15")
    public String password;
}
