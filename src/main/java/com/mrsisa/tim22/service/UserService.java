package com.mrsisa.tim22.service;


import com.mrsisa.tim22.dto.PasswordChangeDTO;
import com.mrsisa.tim22.dto.SystemEntityDTO;
import com.mrsisa.tim22.dto.UserDTO;
import com.mrsisa.tim22.dto.UserRequest;
import com.mrsisa.tim22.model.Role;
import com.mrsisa.tim22.model.SystemEntity;
import com.mrsisa.tim22.model.User;
import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.LoyaltyProgramRepository;
import com.mrsisa.tim22.repository.RegistrationRequestRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SystemEntityRepository systemEntityRepository;
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    public User editUserData(String email, String name, String surname, String phoneNumber, String addressLine) {
        User u = userRepository.findOneByUsername(email);
        u.setName(name);
        u.setSurname(surname);
        u.setPhoneNumber(phoneNumber);
        u.setAddress(addressLine);
        userRepository.save(u);
        System.out.println("Podaci za " + email + " uspesno izmenjeni");
        return u;
    }

    public UserDTO getUserByUsername(String username) {
        User u = userRepository.findOneByUsername(username);
        LoyaltyProgram loyaltyProgram = this.loyaltyProgramRepository.getOne(1);
        String tier = loyaltyProgram.getTierByPoints(u.getLoyaltyPoints());
        int benefits = loyaltyProgram.getDiscountByPoints(u.getLoyaltyPoints());
        return new UserDTO(u.getUsername(), u.getName(), u.getSurname(), u.getPhoneNumber(), u.getAddress(), u.getLoyaltyPoints(), u.getUserPenalties(), tier, benefits);

    }


    public User findByUsername(String email) {
        return userRepository.findOneByUsername(email);

    }

    public User findOneById(Integer id) {
        return userRepository.findOneById(id);
    }

    public User save(UserRequest userRequest) {
        User u = new User();
        u.setUsername(userRequest.getUsername());

        // pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
        // treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        u.setName(userRequest.getName());
        u.setSurname(userRequest.getSurname());
        u.setEnabled(false);
        u.setAddress(userRequest.getAddressLine());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setPhoneNumber(userRequest.getPhoneNumber());
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        List<Role> roles;
        if(userRequest.getUserType().equals("client")){
            roles = roleService.findByName("CLIENT");
        }
        else if(userRequest.getUserType().equals("vacation")){
            roles = roleService.findByName("VACATION_OWNER");
        }
        else if(userRequest.getUserType().equals("vessel")){
            roles = roleService.findByName("SHIP_OWNER");
        }
        else if(userRequest.getUserType().equals("instructor")){
            roles = roleService.findByName("INSTRUCTOR");
        }
        else{
            roles = roleService.findByName("ADMIN");

        }
        u.setRoles(roles);


        return this.userRepository.save(u);
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }


    public int changePassword(PasswordChangeDTO passwordChange) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String current = user.getPassword();
        if (passwordEncoder.matches(passwordChange.getOldPassword(), current) && passwordChange.getNewPassword().equals(passwordChange.getRepeatPassword())) {
            User u = this.userRepository.findOneByUsername(user.getUsername());
            u.setPassword(passwordEncoder.encode(passwordChange.getNewPassword()));
            u.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
            this.saveUser(u);
            return 1;
        }
        return 0;

    }
        public Boolean getSubscribeState (String username,int entityId){
            User u = userRepository.findOneByUsername(username);

            SystemEntity e = systemEntityRepository.findOneById(entityId);

            for (SystemEntity entity : u.getSubscribtions()) {
                System.out.println("ALOOOOOOOOOOO");
                System.out.println(entity.getId());
                System.out.println(entityId);
                if (entity.getId() == entityId) {
                    System.out.println("USAOOOO");
                    return true;
                }
            }

            return false;
        }

        public ArrayList<SystemEntityDTO> getClientSubscriptions (String username){
            User u = userRepository.findOneByUsername(username);
            ArrayList<SystemEntityDTO> entities = new ArrayList<>();
            for (SystemEntity entity : u.getSubscribtions()) {
                if (!entity.isDeleted()) {
                    entities.add(new SystemEntityDTO(entity));
                }
            }
            return entities;


        }

    public int isAdminActive() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User u = userRepository.findOneByUsername(user.getUsername());
        if (u.getLastPasswordResetDate()== null){
            return 0;
        }
        return 1;

    }
}
