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

import com.example.demo.entity.Brand;
import com.example.demo.service.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(MockitoJUnitRunner.class)
public class BrandResourceTest {
	
	@InjectMocks
	BrandResource brandResource;

	@Mock BrandService brandService;

	@Test
	public void whenGettingAllBrands_shouldReturnOKandBrandCollection() throws JsonProcessingException {
		// Mocks
		final List<Brand> brands = new ArrayList<>();
		final Brand brand = new Brand("BMW");
		brands.add(brand);
		Mockito.when(brandService.getAllBrand()).thenReturn(brands);

		// Method call
		final Response response = brandResource.getAllBrand();

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(brands), response.getEntity());
	}

	
	
	@Test
	public void whenGettingBrand_shouldReturnOKandBrand() throws JsonProcessingException {
		// Mocks
		final Brand brand = new Brand("Brand");
		final List<Brand> lc = new ArrayList<Brand>();
		lc.add(brand);
		Mockito.when(brandService.getBrand(1)).thenReturn(brand);

		// Method call
		final Response response = brandResource.getBrand(1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(lc), response.getEntity());
	}
	
	
	@Test
	public void whenAddingCar_shouldReturnCREATEDandCar() {
		// Mocks
		final Brand brand = new Brand("Brand");
		Mockito.when(brandService.addBrand(brand)).thenReturn(brand);

		// Method call
		final Response response = brandResource.addBrand(brand);

		// Assertions
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		assertEquals(brand, response.getEntity());
	}
	
	@Test
	public void whenUpdatingCar_shouldReturnOKandCar() throws JsonProcessingException {
		// Mocks
		final Brand brand = new Brand("Brand");
		Mockito.when(brandService.updateBrand(brand)).thenReturn(brand);

		// Method call
		final Response response = brandResource.updateBrand(1,brand);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(new ObjectMapper().writeValueAsString(brand), response.getEntity());
	}
	
	
	@Test
	public void whenDeletingCar_shouldReturnOKandCar() {
		// Mocks
		final Brand brand = new Brand("Brand");
		Mockito.when(brandService.deleteBrand(1)).thenReturn(brand);

		// Method call
		final Response response = brandResource.deleteBrand(1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(brand, response.getEntity());
	}

}
