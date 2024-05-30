package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.repository.CarRepository;
import com.example.projektbilabonnementgruppe4.repository.viewModel.CarWithStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.*;

/* Klassen er primært skrevet af Lasse Fosgaard, med tilføjelser af Mads Rosenmeyer og Mads Eller relateret til deres kode.
   Klassen indeholder metoder der forbinder controller-laget og repository-laget, samt arbejder på forretningslogik relateret til biler */

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    //Modtager et bilobjekt fra controlleren og sender den til repository-laget for at blive oprettet i databasen
    public void createCar(Car car) {
        carRepository.createCar(car);
    }

    //Modtager et bilobjekt fra controlleren og sender den til repository-laget for at blive opdateret i databasen
    public void updateCar(Car car) {
        carRepository.updateCar(car);
    }

    //Modtager et bilId fra controlleren og sender det til repository-laget for at slette bilen i databasen
    public void deleteCarById(Integer carId) {
        carRepository.deleteCarById(carId);
    }

    //Modtager et stelnummer fra controlleren og sender det til repository-laget for at hente bilen fra databasen
    public Car getCarByFrameNumber(String frameNumber) {
        try {
            return carRepository.getCarByFrameNumber(frameNumber);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Ingen bil fundet med stelnummer: " + frameNumber);
        }
    }

    //Kalder en metode i repository-laget der returnerer alle biler fra databasen
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    //Kalder en metode i repository-laget der returnerer alle biler med status fra databasen
    public List<CarWithStatus> getAllCarsWithStatus() {
        return carRepository.getAllCarsWithStatus();
    }

    //Modtager en query fra controlleren og sender den til repository-laget for at søge efter biler med status der passer til query
    public List<CarWithStatus> searchCarsWithStatus(String query) {
        return carRepository.searchCarsWithStatus(query);
    }

    public double getAveragePriceOfAllCars(){
        return carRepository.getAveragePriceOfAllCars();
    }

    public double getTotalPriceOfAllCars(){
        double totalCarPrice=0;
        for (int i = 0; getAllCars().size()>i; i++){
            totalCarPrice += getAllCars().get(i).getPrice();
        }
        return totalCarPrice;
    }

    public int getTotalRentedCars(){
        int totalRentedCars = 0;
        for (int i = 0; getAllCarsWithStatus().size()>i; i++){
            if (getAllCarsWithStatus().get(i).getCarStatusType().equals("Udlejet")){
                totalRentedCars += 1;
            }
        }
        return totalRentedCars;
    }

    public double getTotalPriceOfRentedCars(){
        double totalPriceOfRentedCars = 0;
        for (int i = 0; getAllCarsWithStatus().size()>i; i++){
            if (getAllCarsWithStatus().get(i).getCarStatusType().equals("Udlejet")){
                totalPriceOfRentedCars += getCarByFrameNumber(getAllCarsWithStatus().get(i).getFrameNumber()).getPrice();
            }
        }
        return totalPriceOfRentedCars;
    }

}
