package com.example.projektbilabonnementgruppe4;

import com.example.projektbilabonnementgruppe4.controller.CarController;
import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.model.RentalAgreement;
import com.example.projektbilabonnementgruppe4.repository.RentalAgreementRepository;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.service.RentalAgreementService;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProjektBilabonnementGruppe4ApplicationTests {

    @InjectMocks
    private CarController carController;

    @InjectMocks
    RentalAgreementService rentalAgreementService;

    @Mock
    RentalAgreementRepository rentalAgreementRepository;

    @Mock
    private CarService carService;

    @Mock
    private CarStatusService carStatusService;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCar() {
        // Arrange
        Car mockCar = new Car();
        mockCar.setFrameNumber("123ABC");

        when(carService.getCarByFrameNumber(anyString())).thenReturn(mockCar);

        // Act
        String viewName = carController.createCar(mockCar);

        // Assert
        verify(carService, times(1)).createCar(mockCar);
        verify(carService, times(1)).getCarByFrameNumber("123ABC");
        verify(carStatusService, times(1)).createCarStatus(mockCar.getCarId());
        assertEquals("redirect:allCarsWithStatus", viewName);
    }

    @Test
    public void testDeleteCar() {
        // Arrange
        Car mockCar = new Car();
        mockCar.setCarId(1);
        mockCar.setFrameNumber("123ABC");

        when(carService.getCarByFrameNumber(anyString())).thenReturn(mockCar);

        // Act
        String viewName = carController.deleteCar("123ABC");

        // Assert
        verify(carService, times(1)).getCarByFrameNumber("123ABC");
        verify(carService, times(1)).deleteCarById(1);
        assertEquals("redirect:cars/allCarsWithStatus", viewName);
    }

    @Test
    public void testSearchCars() {
        // Arrange
        List<CarWithStatus> mockSearchResults = new ArrayList<>();
        when(carService.searchCarsWithStatus(anyString())).thenReturn(mockSearchResults);

        // Act
        String viewName = carController.searchCars("testQuery", model);

        // Assert
        verify(carService, times(1)).searchCarsWithStatus("testQuery");
        verify(model, times(1)).addAttribute("searchResults", mockSearchResults);
        assertEquals("searchResults", viewName);
    }

    @Test
    public void getAllRentalAgreementsReturnsCorrectList() {
        RentalAgreement agreement1 = new RentalAgreement(1, 1, 1, "123", "Location1", LocalDate.now(), LocalDate.now().plusDays(10), "Type1", 1000, 1500);
        RentalAgreement agreement2 = new RentalAgreement(2, 2, 2, "456", "Location2", LocalDate.now(), LocalDate.now().plusDays(20), "Type2", 2000, 2000);
        List<RentalAgreement> expectedList = Arrays.asList(agreement1, agreement2);

        when(rentalAgreementRepository.getAllRentalAgreements()).thenReturn(expectedList);

        List<RentalAgreement> actualList = rentalAgreementService.getAllRentalAgreements();

        assertEquals(expectedList, actualList);
        verify(rentalAgreementRepository, times(1)).getAllRentalAgreements();
    }

    @Test
    public void getRentalAgreementReturnsCorrectAgreement() {
        RentalAgreement expectedAgreement = new RentalAgreement(1, 1, 1, "123", "Location1", LocalDate.now(), LocalDate.now().plusDays(10), "Type1", 1000, 1500);

        when(rentalAgreementRepository.getRentalAgreement(1)).thenReturn(expectedAgreement);

        RentalAgreement actualAgreement = rentalAgreementService.getRentalAgreement(1);

        assertEquals(expectedAgreement, actualAgreement);
        verify(rentalAgreementRepository, times(1)).getRentalAgreement(1);
    }

    @Test
    public void createRentalAgreementCallsRepository() {
        RentalAgreement agreement = new RentalAgreement(1, 1, 1, "123", "Location1", LocalDate.now(), LocalDate.now().plusDays(10), "Type1", 1000, 1500);

        rentalAgreementService.createRentalAgreement(agreement);

        verify(rentalAgreementRepository, times(1)).createRentalAgreement(agreement);
    }

    @Test
    public void updateRentalAgreementCallsRepository() {
        RentalAgreement agreement = new RentalAgreement(1, 1, 1, "123", "Location1", LocalDate.now(), LocalDate.now().plusDays(10), "Type1", 1000, 1500);

        rentalAgreementService.updateRentalAgreement(agreement);

        verify(rentalAgreementRepository, times(1)).updateRentalAgreement(agreement);
    }

    @Test
    public void deleteRentalAgreementCallsRepository() {
        rentalAgreementService.deleteRentalAgreement(1);

        verify(rentalAgreementRepository, times(1)).deleteRentalAgreement(1);
    }
}
