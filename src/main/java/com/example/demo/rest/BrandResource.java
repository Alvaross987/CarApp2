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

import com.example.demo.entity.Brand;
import com.example.demo.service.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/brand")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrandResource {
	@EJB(name = "brandService")
	BrandService brandService;
	
	@GET
	public Response getAllBrand() throws JsonProcessingException {
		return Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(brandService.getAllBrand())).build();
	}
	
	@GET
	@Path("/{brandId}")
	public Response getBrand(@PathParam("brandId") Integer id) throws JsonProcessingException {
		
		Brand brand = brandService.getBrand(id);
		List<Brand> list = new ArrayList<Brand>();
		list.add(brand);
		Response result =  Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(list)).build();
		return result;
	}


	@POST
	public Response addBrand(Brand brand) {

		Brand newBrand = brandService.addBrand(brand);
		return Response.status(Status.CREATED).entity(newBrand).build();
	}


	@PUT
	@Path("/{brandId}")
	public Response updateBrand(@PathParam("brandId") Integer id, Brand brand) throws JsonProcessingException {

		brand.setId(id);

		return Response.status(Status.OK).entity(new ObjectMapper().writeValueAsString(brandService.updateBrand(brand))).build();
	}


	@DELETE
	@Path("/{brandId}")
	public Response deleteBrand(@PathParam("brandId") Integer id) {
		
		return Response.status(Status.OK).entity(brandService.deleteBrand(id)).build();
	}
}
