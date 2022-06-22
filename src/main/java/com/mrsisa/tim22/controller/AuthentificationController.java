package com.mrsisa.tim22.controller;

import com.mrsisa.tim22.dto.*;
import com.mrsisa.tim22.exception.ResourceConflictException;
import com.mrsisa.tim22.model.RegistrationRequest;
import com.mrsisa.tim22.model.User;
import com.mrsisa.tim22.repository.RegistrationRequestRepository;
import com.mrsisa.tim22.service.EmailService;
import com.mrsisa.tim22.service.SystemEntityService;
import com.mrsisa.tim22.service.UserService;
import com.mrsisa.tim22.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/auth")
public class AuthentificationController {


    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SystemEntityService systemEntityService;
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody UserCredentialsDTO authenticationRequest, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }
        catch(AuthenticationException ae){
            return new ResponseEntity<>(new UserTokenState(), HttpStatus.NOT_FOUND);
        }


        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        System.out.println("ULOGOVAN" + user.getUsername());

        String role = user.getRoles().get(0).getName();
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, role), HttpStatus.OK);
    }


    @PostMapping("/usersignup")
    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }
        User user = this.userService.save(userRequest);
        if(userRequest.getUserType().equals("client")){
            emailService.sendActivationEmail(user);
        }
        else if(userRequest.getUserType().equals("admin")){

        }

        else{
            RegistrationRequest request = new RegistrationRequest(user, userRequest.getRegistrationReason());
            this.registrationRequestRepository.save(request);
        }

        System.out.println("Registrovan " + user.getUsername());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/activate/{id}")
    public ResponseEntity<?> activateUser(@PathVariable Integer id){
        User user = userService.findOneById(id);

        if(!user.isEnabled()){
            user.setEnabled(true);
            userService.saveUser(user);
            System.out.println("USPESNA AKTIVACIJA NALOGA!");
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/getAllEntities")
    public ResponseEntity<List<SystemEntityDTO>> getAllEntitites(@RequestParam int startId, @RequestParam int endId){

        return new ResponseEntity<List<SystemEntityDTO>>(systemEntityService.getEntities(startId, endId), HttpStatus.OK);
    }

    @PostMapping(value = "/getFilteredEntities")
    public ResponseEntity<List<SystemEntityDTO>> getFilteredEntities(@RequestBody FilterDTO filters){

        return new ResponseEntity<List<SystemEntityDTO>>(systemEntityService.getFilteredEntities(filters), HttpStatus.OK);
    }


    @PostMapping(value = "/getFilteredEntitiesTotalNumber")
    public ResponseEntity<Integer> getFilteredEntitiesTotalNumber(@RequestBody FilterDTO filters){

        return new ResponseEntity<>(systemEntityService.getFilteredEntitiesTotalNumber(filters), HttpStatus.OK);
    }

    @GetMapping(value="/getImage/{name}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String name, @RequestParam String extension){
        try{
            String path = "src/main/resources/images/" + name + "." + extension;
            FileSystemResource imgFile = new FileSystemResource(path);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(imgFile.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
