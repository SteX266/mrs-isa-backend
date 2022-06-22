package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.*;
import com.mrsisa.tim22.model.*;

import com.mrsisa.tim22.repository.AvailabilityPeriodRepository;

import com.mrsisa.tim22.repository.*;

import com.mrsisa.tim22.repository.ReservationRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SystemEntityService {

    @Autowired
    private SystemEntityRepository systemEntityRepository;
    @Autowired
    private AvailabilityPeriodRepository availabilityPeriodRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VesselRepository vesselRepository;

    @Autowired
    private AdventureRepository adventureRepositry;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ReservationService reservationService;

    public  List<AvailabilityPeriodDTO> getEntityAvailabilityPeriods(int id) {
        ArrayList<AvailabilityPeriodDTO> dtos = new  ArrayList<>();
        for ( AvailabilityPeriod ap : availabilityPeriodRepository.findAvailabilityPeriodBySystemEntity_Id(id)){
            dtos.add(new AvailabilityPeriodDTO(ap));
        }

        return (dtos);
    }


    public List<SystemEntityDTO> getEntities(int startId, int endId){
        ArrayList<SystemEntityDTO> entities = new ArrayList<>();
        List<SystemEntity> allEntities = systemEntityRepository.entitiesBetweenIds(startId, endId);
        for (SystemEntity entity : allEntities){
            if (!entity.isDeleted()){
                entities.add(new SystemEntityDTO(entity));
            }
        }
        return entities;
    }

    public Integer getFilteredEntitiesTotalNumber(FilterDTO filters) {
        List<SystemEntity> filteredList = getFilteredList(filters);
        return filteredList.size();
    }



    public List<SystemEntityDTO> getFilteredEntities(FilterDTO filters) {
        List<SystemEntity> filteredList = getFilteredList(filters);
        ArrayList<SystemEntityDTO> systemEntityDTOS = new ArrayList<>();

        int count = 0;
        for (SystemEntity entity:filteredList){
            count++;
            if(count >= filters.getStartIndex() && count <= filters.getEndIndex()){
                systemEntityDTOS.add(new SystemEntityDTO(entity));
            }
        }
        return systemEntityDTOS;
    }

    public List<SystemEntityDTO> getFilteredEntitiesForCurrentUser(FiltersDTO filterDTO) {
        List<SystemEntityDTO> filteredEntities = new ArrayList<>();
        List<SystemEntity> entities = getFilteredListForCurrentUser(filterDTO);
        System.out.println();
        for(SystemEntity entity: entities) {
            filteredEntities.add(new SystemEntityDTO(entity));
        }
        return filteredEntities;
    }

    private List<SystemEntity> getFilteredListForCurrentUser(FiltersDTO filtersDTO) {
        List<SystemEntity> filteredList = new ArrayList<>();
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom = LocalDateTime.parse(filtersDTO.getDateFrom().replace("T"," ").substring(0,16), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(filtersDTO.getDateTo().replace("T"," ").substring(0,16), formatter);
        for(SystemEntity entity:systemEntityRepository.findSystemEntitiesByOwner_Username(email)){
            if(entity.getPrice() > filtersDTO.getRentalFeeFrom() && entity.getPrice() < filtersDTO.getRentalFeeTo()){
                if(entity.getCancellationFee() > filtersDTO.getCancellationFeeFrom() && entity.getCancellationFee() < filtersDTO.getCancellationFeeTo()) {
                    if (entity.getCapacity() > filtersDTO.getGuestsFrom() && entity.getCapacity() < filtersDTO.getGuestsTo()) {
                        Address address = entity.getAddress();
                        if (address.getStreetName().contains(filtersDTO.getStreet()) && address.getCity().contains(filtersDTO.getCity()) && address.getCountry().contains(filtersDTO.getCountry())) {
                            if(reservationService.isEntityAvailable(entity,dateFrom, dateTo)) {
                                filteredList.add(entity);
                            }
                        }
                    }
                }
            }
        }
        return filteredList;
    }


    private List<SystemEntity> getFilteredList(FilterDTO filters) {
        List<SystemEntity> filteredList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom;
        LocalDateTime dateTo;
        if(filters.getDateFrom() == null || filters.getDateTo() == null){
            dateFrom = null;
            dateTo = null;
        }
        else{
            dateFrom= LocalDateTime.parse(filters.getDateFrom().replace("T"," ").substring(0,16), formatter);
            dateTo = LocalDateTime.parse(filters.getDateTo().replace("T"," ").substring(0,16), formatter);
        }

        for(SystemEntity entity:systemEntityRepository.findAll()){
            if(entity.getEntityType().toString().equals(filters.getType()) || filters.getType().equals("SHOW_ALL"))
                if(entity.getPrice() > filters.getRentalFeeFrom() && entity.getPrice() < filters.getRentalFeeTo()){
                    if(entity.getCancellationFee() > filters.getCancellationFeeFrom() && entity.getCancellationFee() < filters.getCancellationFeeTo()){
                        if(entity.getCapacity() > filters.getGuestsFrom() && entity.getCapacity() < filters.getGuestsTo()){
                            Address address = entity.getAddress();
                            if (address.getStreetName().contains(filters.getStreet()) && address.getCity().contains(filters.getCity()) && address.getCountry().contains(filters.getCountry())){
                                if(dateFrom == null) {
                                    filteredList.add(entity);
                                }
                                else if(reservationService.isEntityAvailable(entity,dateFrom, dateTo)){
                                    filteredList.add(entity);

                                }

                            }
                        }
                    }
                }
            }
        return filteredList;
    }


    public SystemEntityDTO getEntityById(int id) {
        SystemEntity entity = systemEntityRepository.findOneById(id);
        return new SystemEntityDTO(entity);

    }

    public List<SystemEntityDTO> getCurrentUserEntities() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        ArrayList<SystemEntity> entities = systemEntityRepository.findSystemEntitiesByOwner_Username(email);

        ArrayList<SystemEntityDTO> entitiesDTO = new ArrayList<>();
        for (SystemEntity entity : entities) {
            if (!entity.isDeleted()) {
                entitiesDTO.add(new SystemEntityDTO(entity));
            }
        }
        return entitiesDTO;

    }

    public void createSubscribtion(int entityId, String username) {
        SystemEntity e = systemEntityRepository.findOneById(entityId);
        User u = userRepository.findOneByUsername(username);
        e.addSubscriber(u);
        u.addSubscribtion(e);

        systemEntityRepository.save(e);
        userRepository.save(u);


    }

    public void unsubscribe(int entityId, String username) {
        SystemEntity e = systemEntityRepository.findOneById(entityId);
        User u = userRepository.findOneByUsername(username);

        e.removeSubscriber(u);
        u.removeSubscribtion(e);

        systemEntityRepository.save(e);
        userRepository.save(u);
    }

    public double getAverageRating() {
        double sum = 0;
        double len = 0;
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        for (SystemEntity e : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {
            sum += e.getAverageScore();
            len += 1;
        }
        if (len == 0) {
            return 0;
        }
        return sum / len;
    }

    public SystemEntityDTO getBestRated() {
        SystemEntity id = null;
        double bestRating = 0;
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        for (SystemEntity e : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {
            if (e.getAverageScore() > bestRating) {
                bestRating = e.getAverageScore();
                id = e;
            }
        }

        return new SystemEntityDTO(id);
    }

    public SystemEntityDTO getWorstRated() {
        SystemEntity id = null;
        double worst = 5;
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        for (SystemEntity e : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {
            if (e.getAverageScore() < worst) {
                worst = e.getAverageScore();
                id = e;
            }
        }

        return new SystemEntityDTO(id);
    }

    public List<ReservationsReportDTO> getReservationsAmountMonthly() {
        LocalDateTime start = LocalDateTime.now().minusYears(1);
        LocalDateTime now = LocalDateTime.now();
        ArrayList<ReservationsReportDTO> dtos = new ArrayList<>();
        while (!start.isAfter(now)){
            String k = start.getMonth() +" - " + start.getYear();
            dtos.add(new ReservationsReportDTO(k, 0));
            start =start.plusMonths(1);
        }
        start = LocalDateTime.now().minusYears(1);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        for (SystemEntity e : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {
            for (Reservation r : e.getReservations()) {
                if(r.getDateFrom().isBefore(now) && r.getDateFrom().isAfter(start)) {
                    String k = r.getDateFrom().getMonth() + " - " + r.getDateFrom().getYear();
                    for (ReservationsReportDTO report : dtos) {
                        if (report.getName().equals(k)) {
                            report.increaseAmount();
                        }
                    }

                }
            }
        }
        return dtos;
    }

    public List<ReservationsReportDTO> getReservationsAmountYearly() {
        LocalDateTime start = LocalDateTime.now().minusYears(10);
        LocalDateTime now = LocalDateTime.now();
        ArrayList<ReservationsReportDTO> dtos = new ArrayList<>();
        while (!start.isAfter(now)){
            String k = String.valueOf(start.getYear());
            dtos.add(new ReservationsReportDTO(k, 0));
            start =start.plusYears(1);
        }
        start = LocalDateTime.now().minusYears(10);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        for (SystemEntity e : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {
            for (Reservation r : e.getReservations()) {
                if(r.getDateFrom().isBefore(now) && r.getDateFrom().isAfter(start)) {
                    String k = String.valueOf(r.getDateFrom().getYear());
                    for (ReservationsReportDTO report : dtos) {
                        if (report.getName().equals(k)) {
                            report.increaseAmount();
                        }
                    }

                }
            }
        }
        return dtos;
    }



    public List<ReservationsReportDTO> getReservationsAmountWeekly() {
        LocalDateTime start = LocalDateTime.now().minusWeeks(10);
        LocalDateTime now = LocalDateTime.now();
        ArrayList<ReservationsReportDTO> dtos = new ArrayList<>();
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = user.getUsername();
        while (!start.isAfter(now)){
            String k = String.valueOf(start.getDayOfMonth()) +" " + start.getMonth()+ " - " + String.valueOf(start.plusWeeks(1).getDayOfMonth()) +" " + start.plusWeeks(1).getMonth();
            int n =0;
            for (SystemEntity e : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {

                for (Reservation r : e.getReservations()) {
                    if (r.getDateFrom().isAfter(start) && r.getDateFrom().isBefore(start.plusWeeks(1))) {
                        n += 1;
                    }
                }
            }
            dtos.add(new ReservationsReportDTO(k, n));
            start =start.plusWeeks(1);
        }
        return dtos;
    }
    public VesselDTO getDetailVessel(int id) {
        return new VesselDTO(vesselRepository.findVesselById(id));
    }

    public AdventureDTO getDetailAdventures(int id) {
        return new AdventureDTO(adventureRepositry.findAdventureById(id));
    }


    public ListingDTO getDetailVacation(int id) {
        return new ListingDTO(vacationRepository.findVacationById(id));
    }

    private User getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userRepository.findOneByUsername(user.getUsername());
    }
    public Integer generateNextId(){
        Integer id = 0;
        List<SystemEntity> entities = systemEntityRepository.findAll();

        for(SystemEntity entity:entities){
            if (entity.getId() > id){
                id = entity.getId();
            }
        }
        id++;
        return id;
    }
    public boolean saveVessel(VesselDTO vesselDTO) {
        User u = getCurrentUser();
        Address address = new Address(vesselDTO.getCity(), vesselDTO.getCountry(), vesselDTO.getStreetName(), vesselDTO.getStreetNumber());
        Vessel vessel = new Vessel(vesselDTO);
        vessel.setOwner(u);
        vessel.setAddress(address);
        vessel.setId(generateNextId());
        Set<AvailabilityPeriod> availabilityPeriods = createAvailabilityPeriods(vesselDTO.getAvailabilityPeriod(), vessel);
        address.addSystemEntity(vessel);

        List<String> photos;
        try {
            photos = createImages(vesselDTO.getPhotoStrings());
        }
        catch(Exception e){
            photos = new ArrayList<>();
        }
        vessel.setPhotos(photos);

        addressRepository.save(address);
        vesselRepository.save(vessel);
        availabilityPeriodRepository.saveAll(availabilityPeriods);
        return true;
    }

    private List<String> createImages(List<String> photos) throws IOException {
        List<String> images = new ArrayList<>();
        int imageNumber = getNextImageNumber();
        for (String photo:photos){
            byte[]data;
            try{
                data = Base64.getDecoder().decode(photo.split(",")[1]);
            }
            catch(Exception e){

                continue;
            }

            String imageName = "" + imageNumber + ".jpg";

            String imagePath = "src\\main\\resources\\images\\"+imageName;

            try(OutputStream stream = new FileOutputStream(new File(imagePath).getCanonicalFile())){
                stream.write(data);
            }
            images.add(imageName);
            imageNumber++;

        }
        return images;

    }

    private int getNextImageNumber() {
        List<Integer> numbers = new ArrayList<>();
        List<String> photos = getAllPhotos();
        for (String photo:photos){
            String[] tokens = photo.split("\\.");
            numbers.add(Integer.parseInt(tokens[0]));
        }
        int max = 0;
        for(Integer number:numbers){
            if(number > max){
                max = number;
            }
        }
        max++;

        return max;
    }

    private List<String> getAllPhotos() {
        List<SystemEntity> entities = systemEntityRepository.findAll();
        List<String> allPhotos = new ArrayList<>();
        for(SystemEntity entity:entities){
            allPhotos.addAll(entity.getPhotos());
        }
        return allPhotos;
    }

    private Set<AvailabilityPeriod> createAvailabilityPeriods(List<AvailabilityPeriodDTO> availabilityPeriodDTOS, SystemEntity entity) {
        HashSet<AvailabilityPeriod> availabilityPeriods = new HashSet<>();
        for (AvailabilityPeriodDTO availabilityPeriodDTO:availabilityPeriodDTOS) {
            availabilityPeriods.add(createAvailabilityPeriod(availabilityPeriodDTO, entity));
        }
        return availabilityPeriods;
    }
    private AvailabilityPeriod createAvailabilityPeriod(AvailabilityPeriodDTO availabilityPeriodDTO, SystemEntity entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom = LocalDateTime.parse(availabilityPeriodDTO.getDateFrom().replace("T"," ").substring(0,16), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(availabilityPeriodDTO.getDateTo().replace("T"," ").substring(0,16), formatter);
        AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod(dateFrom, dateTo);
        availabilityPeriod.setSystemEntity(entity);
        return availabilityPeriod;
    }

    public boolean deleteEntity(Integer id) {
        SystemEntity entity = systemEntityRepository.findOneById(id);
        if(entity.hasActiveReservations()) {
            return false;
        }
        entity.setDeleted(true);
        systemEntityRepository.save(entity);
        return true;
    }


    public boolean saveListing(ListingDTO listingDTO) {
        User u = getCurrentUser();
        Address address = new Address(listingDTO.getCity(), listingDTO.getCountry(), listingDTO.getStreetName(), listingDTO.getStreetNumber());
        Vacation vacation = new Vacation(listingDTO);
        vacation.setOwner(u);
        vacation.setAddress(address);
        vacation.setId(generateNextId());
        vacation.setAvailabilityPeriod(createAvailabilityPeriods(listingDTO.getAvailabilityPeriod(), vacation));
        address.addSystemEntity(vacation);

        vacation.setId(generateNextId());
        List<String> photos;
        try {
            photos = createImages(listingDTO.getPhotoStrings());
        }
        catch(Exception e){
            photos = new ArrayList<>();
        }
        vacation.setPhotos(photos);
        addressRepository.save(address);
        vacationRepository.save(vacation);
        return true;
    }

    public boolean deleteListing(Long id) {
        vacationRepository.deleteById(id);
        return true;
    }


    public boolean saveAdventure(AdventureDTO adventureDTO) {
        User u = getCurrentUser();
        Address address = new Address(adventureDTO.getCity(), adventureDTO.getCountry(), adventureDTO.getStreetName(), adventureDTO.getStreetNumber());
        Adventure adventure = new Adventure(adventureDTO);
        adventure.setOwner(u);
        adventure.setAddress(address);
        adventure.setId(generateNextId());
        adventure.setAvailabilityPeriod(createAvailabilityPeriods(adventureDTO.getAvailabilityPeriod(), adventure));
        address.addSystemEntity(adventure);

        adventure.setId(generateNextId());
        List<String> photos;
        try {
            photos = createImages(adventureDTO.getPhotoStrings());
        }
        catch(Exception e){
            photos = new ArrayList<>();
        }
        adventure.setPhotos(photos);


        addressRepository.save(address);
        adventureRepositry.save(adventure);
        return true;
    }
    public boolean deleteAdventure(Long id) {
        adventureRepositry.deleteById(id);
        return true;
    }


    public boolean editAmenities(AmenitiesDTO amenitiesDTO) {
        SystemEntity entity = systemEntityRepository.findOneById(amenitiesDTO.getServiceID());
        entity.setAmenities(amenitiesDTO.getAmenityList());
        systemEntityRepository.save(entity);
        return true;
    }
    public boolean editGeneral(GeneralDTO generalDTO) {
        SystemEntity entity = systemEntityRepository.findOneById(generalDTO.getServiceID());
        entity.setName(generalDTO.getName());
        entity.setDescription(generalDTO.getDescription());
        entity.setRulesOfConduct(generalDTO.getRulesOfConduct());
        entity.setPrice(generalDTO.getPrice());
        entity.setCancellationFee(generalDTO.getCancellationFee());
        entity.setCapacity(generalDTO.getCapacity());
        systemEntityRepository.save(entity);
        return true;
    }

    public boolean editAvailabilityPeriod(PeriodsDTO periodsDTO) {
        SystemEntity entity = systemEntityRepository.findOneById(periodsDTO.getServiceID());

        Set<AvailabilityPeriod> availabilityPeriods = entity.getAvailabilityPeriod();
        availabilityPeriodRepository.deleteAll(availabilityPeriods);
        Set<AvailabilityPeriod> newAvailabilityPeriods = createAvailabilityPeriods(periodsDTO.getAvailabilityPeriodDTOS(), entity);
        availabilityPeriodRepository.saveAll(newAvailabilityPeriods);
        systemEntityRepository.save(entity);
        return true;
    }
    public boolean editAddress(AddressDTO addressDTO) {
        SystemEntity entity = systemEntityRepository.findOneById(addressDTO.getServiceID());
        Address oldAddress = addressRepository.getOne(entity.getAddress().getId());
        oldAddress.removeSystemEntity(entity);
        addressRepository.save(oldAddress);

        Address newAddress = new Address(addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getStreetName(), addressDTO.getStreetNumber());
        entity.setAddress(newAddress);
        newAddress.addSystemEntity(entity);
        addressRepository.save(newAddress);
        systemEntityRepository.save(entity);
        return true;
    }
    public boolean editVesselDetails(VesselDetailsDTO detailsDTO) {
        Vessel vessel = vesselRepository.findVesselById(detailsDTO.getServiceID());
        vessel.setMaxSpeed(detailsDTO.getMaxSpeed());
        vessel.setEngineNumber(detailsDTO.getEngineNumber());
        vessel.setEnginePower(detailsDTO.getEnginePower());
        vesselRepository.save(vessel);
        return true;
    }


    public List<RevenurReportDTO> getRevenueReportData(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom = LocalDateTime.parse(startDate.replace("T"," ").substring(0,16), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(endDate.replace("T"," ").substring(0,16), formatter);

        ArrayList<RevenurReportDTO> dtos = new ArrayList<>();
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        String email = user.getUsername();
        for (SystemEntity entity : systemEntityRepository.findSystemEntitiesByOwner_Username(email)) {
            int profit = 0;
            for (Reservation r : entity.getReservations()) {
                if (r.getDateFrom().isAfter(dateFrom) && r.getDateFrom().isBefore(dateTo)) {
                    profit += r.getOwnerPrice();

                }
            }
            dtos.add(new RevenurReportDTO(entity.getName(), profit));
        }
        return dtos;
    }

    public List<RevenurReportDTO> getRevenueReportDataAdmin(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom = LocalDateTime.parse(startDate.replace("T"," ").substring(0,16), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(endDate.replace("T"," ").substring(0,16), formatter);

        ArrayList<RevenurReportDTO> dtos = new ArrayList<>();
        int profit_adventures = 0;
        int profit_vessels = 0;
        int profit_vacations = 0;
        for (SystemEntity entity : systemEntityRepository.findAll()) {

            for (Reservation r : entity.getReservations()) {
                if (r.getDateFrom().isAfter(dateFrom) && r.getDateFrom().isBefore(dateTo)) {
                    if (r.getSystemEntity().getEntityType() == SystemEntityType.ADVENTURE) {
                        profit_adventures += r.getClientPrice() - r.getOwnerPrice();
                    } else if (r.getSystemEntity().getEntityType() == SystemEntityType.VACATION) {
                        profit_vacations += r.getClientPrice() - r.getOwnerPrice();
                    } else {
                        profit_vessels += r.getClientPrice() - r.getOwnerPrice();
                    }
                }
            }
        }
        dtos.add(new RevenurReportDTO("Adventures", profit_adventures));
        dtos.add(new RevenurReportDTO("Vessels", profit_vessels));
        dtos.add(new RevenurReportDTO("Houses", profit_vacations));
        return dtos;
    }

}


