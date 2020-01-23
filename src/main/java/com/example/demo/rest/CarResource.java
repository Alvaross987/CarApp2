package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.transaction.Transactional;
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

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class CarResource {
	
	@EJB(name = "carService	")
	CarService carService;
	
	@GET
	public Response getCars() throws JsonProcessingException {
		return Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(carService.getCars())).build();
	}
	
	@GET
	@Path("{carId}")
	public Response getCar(@PathParam("carId") Integer id) throws JsonProcessingException {
		Car car = carService.getCar(id);
		List<Car> list = new ArrayList<Car>();
		list.add(car);
		Response result = Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(list)).build();
		return result;
	}

	@POST
	public Response addCar(Car car) {

		Car newCar = carService.addCar(car);
		return Response.status(Status.CREATED).entity(newCar).build();
	}
	
	@PUT
	@Path("{carId}")
	public Response updateCar(@PathParam("carId") Integer id, Car car) {

		car.setId(id);

		return Response.accepted(carService.updateCar(car)).build();
	}
	
	@DELETE
	@Path("{carId}")
	public Response deleteCar(@PathParam("carId") Integer id) {

		return Response.accepted(carService.deleteCar(id)).build();
	}
}
