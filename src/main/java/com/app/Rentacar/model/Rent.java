package com.app.Rentacar.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.app.Rentacar.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(unique = true)
	private Integer id;
		
	private LocalDate startRent;

	private LocalDate endRent;
	
	private Double price;
	
	private Double priceRate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "DATETIME")
	private Date dateCreatedRent;

	@Enumerated(EnumType.STRING)
	private StateEnum state;
}