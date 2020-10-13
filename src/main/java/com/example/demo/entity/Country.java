package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name= "country")
@NamedQuery( name= "country.findAll", query = "select c from Country c order by c.country_id")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id", nullable = false)
	private int country_id;

	@Column(name = "name", nullable = false)
	@NotNull(message = "NAME CANNOT BE NULL")
	private String name;
	
	@Column(name = "created_at")
	@JsonIgnore
	private Timestamp created_At;
	
	@Column(name = "last_updated")
	@JsonIgnore
	private Timestamp last_Updated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "Country", fetch = FetchType.LAZY)
	private List<Car> cars;
	
	public Country() {
		super();
	}

	public Country(String name) {
		this.name = name;
	}

	public int getId() {
		return country_id;
	}

	public void setId(int id) {
		this.country_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Timestamp getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Timestamp created_At) {
		this.created_At = created_At;
	}

	public Timestamp getLast_Updated() {
		return last_Updated;
	}

	public void setLast_Updated(Timestamp last_Updated) {
		this.last_Updated = last_Updated;
	}

	@JsonIgnore
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@PrePersist
	public void prePersist() {
		created_At = new Timestamp(new Date().getTime());
		last_Updated = new Timestamp(new Date().getTime());
	}

	@PreUpdate
	public void preUpdate() {
		last_Updated = new Timestamp(new Date().getTime());
	}
}