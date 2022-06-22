package com.mrsisa.tim22.controller;

import com.mrsisa.tim22.dto.*;
import com.mrsisa.tim22.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/editUserData")
    public void editUserData(@RequestParam String email, @RequestParam String name, @RequestParam String surname, @RequestParam String phoneNumber, @RequestParam String addressLine){
        userService.editUserData(email,name,surname,phoneNumber,addressLine);
    }


    @GetMapping(value = "/getUserByUsername")
    public ResponseEntity<UserDTO> getCurrentUser(@RequestParam String username){

        return new ResponseEntity<UserDTO>(userService.getUserByUsername(username), HttpStatus.OK);
    }


    @GetMapping(value ="/change-password")
    public ResponseEntity<Integer> changePassword(@RequestParam String oldEmail,@RequestParam String newEmail,@RequestParam String repeat  ){
        return new  ResponseEntity<Integer>(userService.changePassword(new PasswordChangeDTO(oldEmail,newEmail,repeat)), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/getSubscribeState")
    public ResponseEntity<Boolean> getSubscribeState(@RequestParam String username, @RequestParam int entityId){

        return new ResponseEntity<Boolean>(userService.getSubscribeState(username, entityId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/getClientSubscriptions")
    public ResponseEntity<ArrayList<SystemEntityDTO>> getClientSubscriptions(@RequestParam String username){

        return new ResponseEntity<ArrayList<SystemEntityDTO>>(userService.getClientSubscriptions(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/isAdminActive")
    public ResponseEntity<Integer> isAdminActive(){

        return new ResponseEntity<Integer>(userService.isAdminActive(), HttpStatus.OK);
    }







}
