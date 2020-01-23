package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/country")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {
	@EJB(name = "countryService	")
	CountryService countryService;

	@GET
	public Response getAllCountries() throws JsonProcessingException {
		return Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(countryService.getAllCountries())).build();
	}

	@GET
	@Path("/{countryId}")
	public Response getCountry(@PathParam("countryId") Integer id) throws JsonProcessingException {
		Country country = countryService.getCountry(id);
		List<Country> list = new ArrayList<Country>();
		list.add(country);
		Response result =  Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(list)).build();
		return result;
	}

	@POST
	public Response addCountry(Country country) {

		Country newcountry = countryService.addCountry(country);
		return Response.status(Status.CREATED).entity(newcountry).build();
	}

	@PUT
	@Path("/{countryId}")
	public Response updateCountry(@PathParam("countryId") Integer id, Country country) throws JsonProcessingException {

		country.setId(id);

		return Response.accepted(new ObjectMapper().writeValueAsString(countryService.updateCountry(country))).build();
	}

	@DELETE
	@Path("/{countryId}")
	public Response deleteCountry(@PathParam("countryId") Integer id) {

		return Response.accepted(countryService.deleteCountry(id)).build();
	}
}
