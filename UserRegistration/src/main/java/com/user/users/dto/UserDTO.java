package com.user.users.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message = "please add name")
    @Size(min = 4, max = 20, message = "please enter minimum 4 character and max 20")
    public String userName;
    @NotBlank(message = "email should not blank")
    public String eMail;
    public String address;
    @NotBlank(message = "password should not empty")
    @Size(min = 6, max = 15, message = "please enter the min 6 character and max 15")
    public String password;

    public UserDTO(String userName, String eMail, String address, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.address = address;
        this.password = password;
    }

    public UserDTO() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
