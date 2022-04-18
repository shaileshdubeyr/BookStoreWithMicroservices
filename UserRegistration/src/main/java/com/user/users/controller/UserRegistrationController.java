package com.user.users.controller;

import com.user.users.dto.LoginDTO;
import com.user.users.dto.ResponseDTO;
import com.user.users.dto.UserDTO;
import com.user.users.model.UserData;
import com.user.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {
    @Autowired
    UserService userService;

    /**
     *
     * @param userDTO for registration object
     * @return user status
     * @Purpose to publish data to queue
     */
    @PostMapping("/registration")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    /**
     *
     * @param loginDTO
     * @return ResponseDTO status to the user
     * @throws NoSuchFieldException
     */
    @PostMapping("/login/{token}")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO, @PathVariable String token) throws NoSuchFieldException {
        return userService.login(loginDTO.eMail, loginDTO.password, token);
    }

    /**
     *
     * @param forgetDTO
     * @param token for verification user
     * @return ResponseDTO status to the user
     */
    @PostMapping("/forgetPassword/{token}")
    public ResponseEntity<ResponseDTO> forgetPassword(@RequestBody @Valid LoginDTO forgetDTO, @RequestParam String token){
        return  userService.forgetPassword(forgetDTO, token);
    }

    /**
     *
     * @param resetPasswordDTO
     * @return ResponseDTO status to the user
     */
    @PostMapping("/reset/password")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestBody @Valid LoginDTO resetPasswordDTO, @RequestParam("token") String token) {
        return userService.resetPassword(resetPasswordDTO, token);
    }


    /**
     *
     * @param token for verification user
     * @return ResponseDTO status to the user
     * @Puropse to retrive all the user data from database
     */
    @GetMapping("/getAllUsers")
    public  ResponseEntity<ResponseDTO> getAllById(@RequestParam String token){
        return userService.getAllUsersById(token);
    }

    /**
     *
     * @param token for verification user
     * @return ResponseDTO status to the user
     * @Puropse to retrive the user data from database by id
     */
    @GetMapping("/getById")
    public ResponseEntity<UserData> getById(@RequestParam String token){
        return userService.getUserById(token);
    }

    /**
     *
     * @param token for verification user
     * @return ResponseDTO status to the user
     * @Purpose to verify the user with token
     */
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verify(@PathVariable String token){
        System.out.println(token.toString());
        return userService.verify(token);
    }

    /**
     *
     * @param token to verify the user
     * @param userDTO contains updated data of the user
     * @return ResponseDTO to return the status of the user
     */
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable String token, @RequestBody @Valid UserDTO userDTO){
        return userService.UpdateData(token, userDTO);
    }

    /**
     *
     * @param id deleting the user with the id
     * @return ResponseDTO to the user
     * @purpose to delete the user if id is present
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }
}
