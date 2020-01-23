package com.example.demo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;

import com.example.demo.entity.Country;


@Stateless
public class CountryService {
	
	@PersistenceContext(unitName = "postg")
	EntityManager em;
	
	@Inject
	@Metric(name="total_country_execution", absolute = true)
	Counter countryExecutions;
	
	@Counted(name="getAllCountry_executions",absolute = true)
	public List<Country> getAllCountries() {
		countryExecutions.inc();
		return em.createNamedQuery("country.findAll", Country.class).getResultList();
	}
	
	@Counted(name="getCountry_executions",absolute = true)
	public Country getCountry(int id) {
		Country country = em.find(Country.class, id);
		countryExecutions.inc();
		return country;

	}

	@Counted(name="addCountry_executions",absolute = true)
	public Country addCountry(Country country) {
		em.persist(country);
		countryExecutions.inc();
		return country;
	}

	@Counted(name="updateCountry_executions",absolute = true)
	public Country updateCountry(Country country) {
		int id = country.getId();
		Country country2 = em.find(Country.class, id);
		country2.setName(country.getName());
		em.persist(country2);
		countryExecutions.inc();
		return country2;
	}

	@Counted(name="deleteCountry_executions",absolute = true)
	public Country deleteCountry(int id) {
		Country country = em.find(Country.class, id);
		em.remove(country);
		countryExecutions.inc();
		return country;

	}
}
