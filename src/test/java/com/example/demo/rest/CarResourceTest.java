package com.example.demo.rest;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Car;
import com.example.demo.entity.Country;
import com.example.demo.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceTest {

	@InjectMocks
	CarResource carResource;

	@Mock CarService carService;
	
	
	@Test
	public void whenGettingCars_shouldReturnOKandCarCollection() throws JsonProcessingException {
		// Mocks
		final List<Car> cars = new ArrayList<>();
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		cars.add(car);
		Mockito.when(carService.getCars()).thenReturn(cars);

		// Method call
		final Response response = carResource.getCars();

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(cars), response.getEntity());
	}

	
	
	@Test
	public void whenGettingCar_shouldReturnOKandCar() throws JsonProcessingException {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		final List<Car> lc = new ArrayList<Car>();
		lc.add(car);
		Mockito.when(carService.getCar(1)).thenReturn(car);

		// Method call
		final Response response = carResource.getCar(1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(lc) , response.getEntity());
	}
	
	
	@Test
	public void whenAddingCar_shouldReturnCREATEDandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(carService.addCar(car)).thenReturn(car);

		// Method call
		final Response response = carResource.addCar(car);

		// Assertions
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		assertEquals(car, response.getEntity());
	}
	
	@Test
	public void whenUpdatingCar_shouldReturnACCEPTEDandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(carService.updateCar(car)).thenReturn(car);

		// Method call
		final Response response = carResource.updateCar(1,car);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(car, response.getEntity());
	}
	
	
	@Test
	public void whenDeletingCar_shouldReturnACCEPTEDandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(carService.deleteCar(1)).thenReturn(car);

		// Method call
		final Response response = carResource.deleteCar(1);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(car, response.getEntity());
	}
}