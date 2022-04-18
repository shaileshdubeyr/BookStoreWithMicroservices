package com.user.users.services;


import com.user.users.configuration.RabbitMqConfiguration;
import com.user.users.dto.LoginDTO;
import com.user.users.dto.ResponseDTO;
import com.user.users.dto.UserDTO;
import com.user.users.exception.UserException;
import com.user.users.model.UserData;
import com.user.users.repository.UserRepository;
import com.user.users.utility.UserUtility;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements  IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtility userUtility;

    @Autowired
    EmailService emailService;

    @Autowired
    RabbitTemplate template;

    /**
     *
     * @param userDTO to save user data
     * @return ResponseDto as a status
     * @Purpose to save the data into the database and transfer to the amqp server
     */
    @Override
    public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO) {
        if(userDTO.eMail.equals(userRepository.findByeMail(userDTO.eMail))){
            throw new UserException("user is exist", UserException.ExceptionType.USER_ALREADY_EXISTS);
        }
        UserData userData = userRepository.save( new UserData(userDTO));
        String token = userUtility.createToken(userData.getId());
        if (userData != null){
            template.convertAndSend(RabbitMqConfiguration.EXCHANGE,RabbitMqConfiguration.ROUTING_A,userData);
            ResponseDTO responseDTO =  new ResponseDTO("data saved with", userData, token);
            System.out.println(responseDTO);
            return new  ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        }else {
            throw new UserException("user is not valid ", UserException.ExceptionType.NOT_VALID_USER);
        }

    }

    /**
     *
     * @param token to verify the user
     * @return response of the user with confirmation message
     */
    @Override
    public ResponseEntity<UserData> getUserById(String token) {
        int id_token = userUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(id_token);
        if(userData.isPresent()){
            ResponseDTO responseDTO = new ResponseDTO("get call success", userData, " "+id_token);
            return new ResponseEntity<UserData>(userData.get(), HttpStatus.OK);
        }else {
            throw new UserException("user is not present", UserException.ExceptionType.NOT_VALID_USER);
        }

    }

    /**
     *
     * @param token to verify the user
     * @param userDTO metadata of the user
     * @return the responseDTO if data updated
     */
    @Override
    public ResponseEntity<ResponseDTO> UpdateData(String token, UserDTO userDTO) {
        UserData userData = new UserData(userDTO);
        int token_id = userUtility.decodeToken(token);
        userData.setId(token_id);
        if(userRepository.findByeMail(userData.getEMail()).equals(userData.getEMail())){
            userRepository.save(userData);
            ResponseDTO responseDTO =  new ResponseDTO("data saved with ", userData, "token decoded");
            return new  ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        }else {
            throw new UserException("user is not register", UserException.ExceptionType.INVALID_DATA);
        }

    }

    /**
     *
     * @param token to verify the user
     * @return the responseDTO if all user found
     */
    @Override
    public ResponseEntity<ResponseDTO> getAllUsersById(String token) {
        List<UserData> userData = userRepository.findAll();
        if(userData.isEmpty()){
            throw new UserException("no user is registered", UserException.ExceptionType.NO_USER_REGISTERED);
        }
        ResponseDTO responseDTO = new ResponseDTO("get all", userData, "verify user by ");
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /**
     *
     * @param email user email for login
     * @param password user password for login
     * @param token to verify the user
     * @return the responseDTO if user logged in success
     * @throws NoSuchFieldException if user not found it will throw exception
     */
    @Override
    public ResponseEntity<ResponseDTO> login(String email, String password, String token) throws NoSuchFieldException {
        UserData userData = userRepository.findByeMail(email);
        int tokId = userUtility.decodeToken(token);
            if(userData.getEMail().equals(email) && userData.getPassword().equals(password) && tokId == userData.getId()) {
                String token_generation = userUtility.createToken(userData.getId());
                ResponseDTO responseDTO = new ResponseDTO("login success", userData, token_generation);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            } else {
                throw new UserException("no match found", UserException.ExceptionType.NOT_VALID_USER);
            }
    }

    /**
     *
     * @param token to verify the user
     * @return the response of the object if user is verified
     */
    @Override
    public ResponseEntity<ResponseDTO> verify(String token) {
        System.out.println(token);
        System.out.println(userUtility.decodeToken(token));
        Optional<UserData> userData = userRepository.findById(userUtility.decodeToken(token));
        if (userData.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO("ERROR: Invalid token", null, token);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
        }

        userData.get().setVerified(true);
        userRepository.save(userData.get());

        ResponseDTO responseDTO = new ResponseDTO(" The employee has been verified ", userData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    /**
     *
     * @param forgetDTO new password containing the object
     * @param token to verify the user
     * @return ResponseDTO return the response if password in changed
     */
    @Override
    public ResponseEntity<ResponseDTO> forgetPassword(LoginDTO forgetDTO, String token) {
        UserData userData = userRepository.findByeMail(forgetDTO.eMail);
        emailService.forgetPassword(userData,token);
        return null;
    }


    /**
     *
     * @param resetPasswordDTO have the new password
     * @return ResponseDto return the object with changed password
     * @purpose to change the password
     */
    @Override
    public ResponseEntity<ResponseDTO> resetPassword(LoginDTO resetPasswordDTO, String token) {
       Optional<UserData> userData =  userRepository.findById(userUtility.decodeToken(token));
       if(userData.isPresent()){
           userData.get().setPassword(resetPasswordDTO.password);
           userRepository.save(userData.get());
           ResponseDTO responseDTO = new ResponseDTO("password reset successful", userData, token);
           return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
       } else {
           throw new UserException("user is not verified", UserException.ExceptionType.INVALID_DATA);
       }
    }

    /**
     *
     * @param id to fetch the user
     * @return the ResponseDTO if user deleted
     * @Purpose to delete the user
     */
    @Override
    public ResponseEntity<ResponseDTO> deleteUser(int id) {
        userRepository.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO("delete success by id ", id, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
