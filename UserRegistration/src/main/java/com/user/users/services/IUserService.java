package com.user.users.services;

import com.user.users.dto.LoginDTO;
import com.user.users.dto.ResponseDTO;
import com.user.users.dto.UserDTO;
import com.user.users.model.UserData;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO);
    public ResponseEntity<UserData> getUserById(String token);
    public ResponseEntity<ResponseDTO> UpdateData(String token, UserDTO userDTO);
    public ResponseEntity<ResponseDTO> getAllUsersById(String token);
    public ResponseEntity<ResponseDTO> login(String email, String password, String token) throws NoSuchFieldException;
    ResponseEntity<ResponseDTO> verify(String token);
    ResponseEntity<ResponseDTO> forgetPassword(LoginDTO forgetDTO, String token);
    ResponseEntity<ResponseDTO> resetPassword(LoginDTO resetPasswordDTO, String token);
    ResponseEntity<ResponseDTO> deleteUser(int id);
}