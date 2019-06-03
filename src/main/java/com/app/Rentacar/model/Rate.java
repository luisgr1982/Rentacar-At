package com.app.Rentacar.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(unique = true)
	private Integer id;
	
	@Column(nullable = false)
	private LocalDate startRate;
	
	private LocalDate endRate;
	
	@Digits(integer=10, fraction=2)
	private double priceRate;
	
	@ManyToMany (fetch = FetchType.LAZY)
	private Set<Car> cars = new HashSet<Car>();
	
	@Temporal(TemporalType.TIMESTAMP)
 	@Column(name = "date_created_rate", columnDefinition = "DATETIME")
	private Date dateCreatedRate;
}