package com.example.demo.metric;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("Coffee")
public class CoffeeResource {

	@Inject
	Coffee coffees;
	@GET
	@Path("A")
	public String getCoffeeA() {
		return coffees.retrieveCoffeeA();
	}
	
	@GET
	@Path("B")
	public String getCoffeeB() {
		return coffees.retrieveCoffeeB();
	}
	
	@GET
	@Path("C")
	public String getCoffeeC() {
		return coffees.retrieveCoffeeC();
	}
	
	@GET
	@Path("GTest")
	public int GaugeTest() {
		return coffees.GaugeTest();
	}
}
