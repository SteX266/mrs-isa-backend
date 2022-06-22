package com.mrsisa.tim22.controller;

import com.mrsisa.tim22.dto.*;
import com.mrsisa.tim22.model.Utility;
import com.mrsisa.tim22.service.SystemEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/entity", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemEntityController {

    @Autowired
    private SystemEntityService systemEntityService;

    @GetMapping(value = "/getAllEntities")
    public ResponseEntity<List<SystemEntityDTO>> getAllEntitites(){

        return new ResponseEntity<>(systemEntityService.getEntities(1,3), HttpStatus.OK);
    }

    @GetMapping(value ="getEntityById")
    public ResponseEntity<SystemEntityDTO> getEntityById(@RequestParam int id){
        return new ResponseEntity<>(systemEntityService.getEntityById(id), HttpStatus.OK);
    }


    @PostMapping(value ="/search")
    public ResponseEntity<SystemEntityDTO> search(@RequestBody SearchDTO searchDTO){
        return new ResponseEntity<>(new SystemEntityDTO(), HttpStatus.OK);
    }



    @GetMapping(value ="getCurrentUserEntities")
    public ResponseEntity<List<SystemEntityDTO>> getCurrentUserEntities(){


        return new ResponseEntity<>(systemEntityService.getCurrentUserEntities(), HttpStatus.OK);
    }
    @GetMapping(value = "/getEntityAvailabilityPeriods")
    public ResponseEntity<List<AvailabilityPeriodDTO>> getEntityAvailabilityPeriods(@RequestParam int id){
        return new ResponseEntity<>(systemEntityService.getEntityAvailabilityPeriods(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value ="createSubscription")
    public void createSubscription(@RequestParam String username, @RequestParam int entityId){
        systemEntityService.createSubscribtion(entityId, username);
    }
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value ="unsubscribe")
    public void unsubscribe(@RequestParam String username, @RequestParam int entityId){
        systemEntityService.unsubscribe(entityId, username);
    }
    @GetMapping(value ="getDetailVessel")
    public ResponseEntity<VesselDTO> getDetailVessel(@RequestParam int id){
        return new ResponseEntity<>(systemEntityService.getDetailVessel(id), HttpStatus.OK);
    }

    @GetMapping(value ="getAverageRating")
    public double getAverageRating(){
        return systemEntityService.getAverageRating();
    }

    @GetMapping(value ="getBestRated")
    public  ResponseEntity<SystemEntityDTO> getBestRated(){
        return new ResponseEntity<>(systemEntityService.getBestRated(), HttpStatus.OK);
    }
    @GetMapping(value ="getDetailAdventure")
    public ResponseEntity<AdventureDTO> getDetailAdventure(@RequestParam int id){
        return new ResponseEntity<>(systemEntityService.getDetailAdventures(id), HttpStatus.OK);
    }
    @GetMapping(value ="getDetailVacation")
    public ResponseEntity<ListingDTO> getDetailVacation(@RequestParam int id){
        return new ResponseEntity<>(systemEntityService.getDetailVacation(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping(value ="getWorstRated")
    public  ResponseEntity<SystemEntityDTO> getWorstRated(){
        return new ResponseEntity<>(systemEntityService.getWorstRated(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping(value ="getReservationsAmountMonthly")
    public  ResponseEntity<List<ReservationsReportDTO>> getReservationsAmountMonthly(){
        return new ResponseEntity<>(systemEntityService.getReservationsAmountMonthly(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping(value ="getReservationsAmountYearly")
    public  ResponseEntity<List<ReservationsReportDTO>> getReservationsAmountYearly(){
        return new ResponseEntity<>(systemEntityService.getReservationsAmountYearly(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping(value ="getReservationsAmountWeekly")
    public  ResponseEntity<List<ReservationsReportDTO>> getReservationsAmountWeekly(){
        return new ResponseEntity<>(systemEntityService.getReservationsAmountWeekly(), HttpStatus.OK);
    }

    // CREATE AND EDIT VESSEL
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/createVessel")
    public ResponseEntity<String> createVessel(@RequestBody VesselDTO vesselDTO) {
        if(systemEntityService.saveVessel(vesselDTO)) {
            return new ResponseEntity<>("Successfully created vessel.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't create vessel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @DeleteMapping("/deleteVessel")
    public ResponseEntity<String> deleteVessel(@RequestParam Integer id) {
        if(systemEntityService.deleteEntity(id)) {
            return new ResponseEntity<>("Successfully deleted vessel.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't delete vessel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE AND EDIT LISTING
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/createListing")
    public ResponseEntity<String> createListing(@RequestBody ListingDTO listingDTO) {
        if(systemEntityService.saveListing(listingDTO)) {
            return new ResponseEntity<>("Successfully created listing.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't create listing.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @DeleteMapping("/deleteListing")
    public ResponseEntity<String> deleteListing(@RequestParam Integer id) {
        if(systemEntityService.deleteEntity(id)) {
            return new ResponseEntity<>("Successfully deleted listing.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't delete listing.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE AND EDIT ADVENTURE
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/createAdventure")
    public ResponseEntity<String> createAdventure(@RequestBody AdventureDTO adventureDTO) {
        if(systemEntityService.saveAdventure(adventureDTO)) {
            return new ResponseEntity<>("Successfully created listing.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't create listing.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @DeleteMapping("/deleteAdventure")
    public ResponseEntity<String> deleteAdventure(@RequestParam Long id) {
        if(systemEntityService.deleteAdventure(id)) {
            return new ResponseEntity<>("Successfully deleted adventure.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't delete adventure.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  HERE LIES THE EDIT
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/editGeneral")
    public ResponseEntity<String> editGeneral(@RequestBody GeneralDTO generalDTO) {
        if(systemEntityService.editGeneral(generalDTO)) {
            return new ResponseEntity<>("Successfully edited general information.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't edited general information.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/editAmenities")
    public ResponseEntity<String> editAmenities(@RequestBody AmenitiesDTO amenitiesDTO) {
        if(systemEntityService.editAmenities(amenitiesDTO)) {
            return new ResponseEntity<>("Successfully edited amenities.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't edit amenities.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/editAvailabilityPeriod")
    public ResponseEntity<String> editAvailabilityPeriod(@RequestBody PeriodsDTO periodsDTO) {
        if(systemEntityService.editAvailabilityPeriod(periodsDTO)) {
            return new ResponseEntity<>("Successfully edited availability period.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't edited availability period.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/editAddress")
    public ResponseEntity<String> editAddress(@RequestBody AddressDTO addressDTO) {
        if(systemEntityService.editAddress(addressDTO)) {
            return new ResponseEntity<>("Successfully edited address.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't edited address.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/editVesselDetails")
    public ResponseEntity<String> editVesselDetails(@RequestBody VesselDetailsDTO detailsDTO) {
        if(systemEntityService.editVesselDetails(detailsDTO)) {
            return new ResponseEntity<>("Successfully edited details.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't edited details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // HERE LIE THE AMENITY LISTS

    @GetMapping("/getVesselUtilities")
    public ResponseEntity<List<Utility>> getVesselUtilities() {
        List<Utility> utilities = new ArrayList<Utility>(Arrays.asList(Utility.AC, Utility.FIRST_AID_KIT, Utility.FISHING_NET, Utility.FISHING_POLES, Utility.FRIDGE, Utility.GPS, Utility.PET_FRIENDLY, Utility.SONAR));

        return new ResponseEntity<>(utilities, HttpStatus.OK);
    }
    @GetMapping("/getAdventureUtilities")
    public ResponseEntity<List<Utility>> getAdventureUtilities() {
        List<Utility> utilities = new ArrayList<Utility>(Arrays.asList(Utility.FIRST_AID_KIT, Utility.FISHING_NET, Utility.FISHING_POLES, Utility.GPS, Utility.PET_FRIENDLY));

        return new ResponseEntity<>(utilities, HttpStatus.OK);
    }
    @GetMapping("/getListingUtilities")
    public ResponseEntity<List<Utility>> getListingUtilities() {
        List<Utility> utilities = new ArrayList<Utility>(Arrays.asList(Utility.AC, Utility.TV, Utility.FRIDGE, Utility.BBQ, Utility.GYM, Utility.KITCHEN, Utility.HEATING, Utility.WASHER, Utility.WIFI, Utility.PET_FRIENDLY));

        return new ResponseEntity<>(utilities, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping(value ="getRevenueReportData")
    public  ResponseEntity<List<RevenurReportDTO>> getRevenueReportData(@RequestParam String startDate,@RequestParam String endDate){
        return new ResponseEntity<>(systemEntityService.getRevenueReportData(startDate,endDate), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value ="getRevenueReportDataAdmin")
    public  ResponseEntity<List<RevenurReportDTO>> getRevenueReportDataAdmin(@RequestParam String startDate,@RequestParam String endDate){
        return new ResponseEntity<>(systemEntityService.getRevenueReportDataAdmin(startDate,endDate), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/getFilteredEntities")
    public ResponseEntity<List<SystemEntityDTO>> getFilteredEntities(@RequestBody FiltersDTO filtersDTO) {
        List<SystemEntityDTO> entityDTOList = systemEntityService.getFilteredEntitiesForCurrentUser(filtersDTO);
        return new ResponseEntity<>(entityDTOList, HttpStatus.OK);
    }

}
