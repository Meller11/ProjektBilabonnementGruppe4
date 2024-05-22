package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.repository.CarStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/* Klassen er skrevet af Lasse Fosgaard.
   Klassen indeholder metoder til at forbinde controller-laget og repository-laget, samt arbejder på forretningslogik relateret til bilstatus */

@Service
public class CarStatusService {

    @Autowired
    private CarStatusRepository carStatusRepository;

    //Modtager et carId som parameter og sender det til repository-laget for at oprette en bilstatus i databasen
    public void createCarStatus(Integer carId) {
        carStatusRepository.createCarStatus(carId, "Klar til udlejning", LocalDate.now());
    }

    //Modtager et carId og en ny status som parameter og sender det (samt datoen for opdateringen) til repository-laget for at opdatere bilstatus i databasen
    public void updateCarStatus(Integer carId, String newStatus) {
        carStatusRepository.updateCarStatus(carId, newStatus, LocalDate.now());
    }
}

