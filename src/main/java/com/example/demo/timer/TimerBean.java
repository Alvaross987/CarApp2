package com.example.demo.timer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.example.demo.db.FlywayUtil;
import com.example.demo.entity.Car;


@Singleton
@Startup
public class TimerBean {

	Logger log = Logger.getLogger(TimerBean.class);

	@PersistenceContext(unitName = "postg")
	EntityManager em;
	
	@PostConstruct
	private void migration() {
		FlywayUtil.migrate();
	}

	@Schedule(hour = "*", minute="*/10", persistent = false)
	public void automaticallyScheduled() {
		checkCars();

	}

	public void checkCars() {
		log.info("Cheking Cars");
		List<Car> list = em.createNamedQuery("car.findAll", Car.class).getResultList();
		list.forEach((car) ->{
			car.setChecked(1);
			em.persist(car);
		});
		log.info("All cars have been checked");
	}

}
