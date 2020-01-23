package com.example.demo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;

import com.example.demo.entity.Brand;

@Stateless
public class BrandService {
	
	@PersistenceContext(unitName = "postg")
	EntityManager em;
	
	@Inject
	@Metric(name = "total_brand_executions", absolute = true)
	Counter brandExecutions;
	
	@Counted(name="getAllBrand_executions",absolute = true)
	public List<Brand> getAllBrand() {
		
		brandExecutions.inc();
		return em.createNamedQuery("brand.findAll", Brand.class).getResultList();
	}
	
	@Counted(name="getBrand_executions",absolute = true)
	public Brand getBrand(int id) {
		Brand brand = em.find(Brand.class, id);
		brandExecutions.inc();
		return brand;

	}
	
	@Counted(name="addBrand_executions",absolute = true)
	public Brand addBrand(Brand brand) {
		em.persist(brand);
		brandExecutions.inc();
		return brand;
	}
	
	@Counted(name="updateBrand_executions",absolute = true)
	public Brand updateBrand(Brand brand) {
		int id = brand.getId();
		Brand brand2 = em.find(Brand.class, id);
		brand2.setName(brand.getName());
		em.persist(brand2);
		brandExecutions.inc();
		return brand2;
	}
	
	@Counted(name="deleteBrand_executions",absolute = true)
	public Brand deleteBrand(int id) {
		Brand brand = em.find(Brand.class, id);
		em.remove(brand);
		brandExecutions.inc();
		return brand;

	}
}
