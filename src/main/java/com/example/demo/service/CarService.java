package com.example.demo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;

import com.example.demo.entity.Car;

@Stateless
public class CarService {
	
	@PersistenceContext(unitName = "postg")
	EntityManager em;

	@Inject
	@Metric(name="total_car_execution", absolute = true)
	Counter carExecutions;
	
	@Counted(name="getAllCar_executions",absolute = true)
	public List<Car> getCars() {
		
		carExecutions.inc();
		return em.createNamedQuery("car.findAll", Car.class).getResultList();
	}
	@Counted(name="getCar_executions",absolute = true)
	public Car getCar(int id) {
		Car car = em.find(Car.class, id);
		carExecutions.inc();
		return car;

	}
	@Counted(name="addCar_executions",absolute = true)
	public Car addCar(Car car) {
		em.persist(car);
		carExecutions.inc();
		return car;
	}
	@Counted(name="updateCar_executions",absolute = true)
	public Car updateCar(Car car) {
		int id = car.getId();
		Car car2 = em.find(Car.class, id);
		car2.setBrand(car.getBrand());
		car2.setModel(car.getModel());
		car2.setColor(car.getColor());
		car2.setCountry(car.getCountry());
		car2.setRegistration(car.getRegistration());
		car2.setChecked(0);
		em.persist(car2);
		carExecutions.inc();
		return car2;
	}
	@Counted(name="deleteCar_executions",absolute = true)
	public Car deleteCar(int id) {
		Car car = em.find(Car.class, id);
		em.remove(car);
		carExecutions.inc();
		return car;

	}
	
}
