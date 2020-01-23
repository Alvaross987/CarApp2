package com.example.demo.metric;

import javax.inject.Inject;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;

public class Coffee {

	@Inject
	@Metric(name="total_coffees_consumed", absolute = true)
	Counter coffeesConsumed;
	
	public void oneCoffee() {
		coffeesConsumed.inc();
	}
	
	@Counted(name="total_coffeesA_retrieved", absolute = true)
	public String retrieveCoffeeA() {
		oneCoffee();
		return "CoffeeA!";
	}
	
	@Counted(name="total_coffeesB_retrieved", absolute = true)
	public String retrieveCoffeeB() {
		oneCoffee();
		return "CoffeeB!";
	}
	
	@Counted(name="total_coffeesC_retrieved", absolute = true)
	public String retrieveCoffeeC() {
		oneCoffee();
		return "CoffeeC!";
	}
	
	@Gauge(unit = "Test")
	public int GaugeTest() {
		return 27;
	}
	
}
