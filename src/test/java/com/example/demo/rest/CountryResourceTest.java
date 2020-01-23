package com.example.demo.rest;

import static org.junit.Assert.assertEquals;

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

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class CountryResourceTest {

	@InjectMocks
	CountryResource countryResource;

	@Mock CountryService countryService;

	@Test
	public void whenGettingAllCountries_shouldReturnOKandCountryCollection() throws JsonProcessingException {
		// Mocks
		final List<Country> countries = new ArrayList<>();
		final Country country = new Country("SPAIN");
		countries.add(country);
		Mockito.when(countryService.getAllCountries()).thenReturn(countries);

		// Method call
		final Response response = countryResource.getAllCountries();

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(countries), response.getEntity());
	}

	
	
	@Test
	public void whenGettingCountry_shouldReturnOKandCountry() throws JsonProcessingException {
		// Mocks
		final Country country = new Country("SPAIN");
		final List<Country> countries = new ArrayList<Country>();
		countries.add(country);
		Mockito.when(countryService.getCountry(1)).thenReturn(country);

		// Method call
		final Response response = countryResource.getCountry(1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(countries), response.getEntity());
	}
	
	
	@Test
	public void whenAddingCountry_shouldReturnCREATEDandCountry() {
		// Mocks
		final Country country = new Country("SPAIN");
		Mockito.when(countryService.addCountry(country)).thenReturn(country);

		// Method call
		final Response response = countryResource.addCountry(country);

		// Assertions
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		assertEquals(country, response.getEntity());
	}
	
	@Test
	public void whenUpdatingCountry_shouldReturnACCEPTEDandCountry() throws JsonProcessingException {
		// Mocks
		final Country country = new Country("SPAIN");
		Mockito.when(countryService.updateCountry(country)).thenReturn(country);

		// Method call
		final Response response = countryResource.updateCountry(1,country);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(country), response.getEntity());
	}
	
	
	@Test
	public void whenDeletingCountry_shouldReturnACCEPTEDandCountry() {
		// Mocks
		final Country country = new Country("SPAIN");
		Mockito.when(countryService.deleteCountry(1)).thenReturn(country);

		// Method call
		final Response response = countryResource.deleteCountry(1);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(country, response.getEntity());
	}

}
