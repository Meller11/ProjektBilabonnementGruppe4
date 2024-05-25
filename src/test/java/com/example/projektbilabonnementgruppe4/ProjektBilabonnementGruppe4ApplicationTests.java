package com.example.projektbilabonnementgruppe4;

import com.example.projektbilabonnementgruppe4.controller.CarController;
import com.example.projektbilabonnementgruppe4.model.Car;
import com.example.projektbilabonnementgruppe4.service.CarService;
import com.example.projektbilabonnementgruppe4.service.CarStatusService;
import com.example.projektbilabonnementgruppe4.viewModel.CarWithStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProjektBilabonnementGruppe4ApplicationTests {

    @InjectMocks
    private CarController carController;

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
        assertEquals("redirect:/cars/allCarsWithStatus", viewName);
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
}
