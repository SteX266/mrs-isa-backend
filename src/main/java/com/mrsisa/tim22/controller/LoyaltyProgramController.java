package com.mrsisa.tim22.controller;


import com.mrsisa.tim22.model.LoyaltyProgram;
import com.mrsisa.tim22.service.LoyaltyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/loyalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoyaltyProgramController {
    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @GetMapping(value = "/getLoyalty")
    public ResponseEntity<LoyaltyProgram> getLoyalty(){
        return new ResponseEntity<>(loyaltyProgramService.getLoyalty(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/editLoyalty")
    public void editLoyalty(@RequestParam int id, @RequestParam int platinumLimit, @RequestParam int pointsForBusiness, @RequestParam int pointsPerReservation, @RequestParam int silverLimit,@RequestParam int goldLimit,@RequestParam int goldDiscount,@RequestParam int silverDiscount,@RequestParam int platinumDiscount){
        loyaltyProgramService.save(new LoyaltyProgram(id,pointsPerReservation,pointsForBusiness,silverLimit,silverDiscount,goldLimit,goldDiscount,platinumLimit,platinumDiscount));
    }
}
