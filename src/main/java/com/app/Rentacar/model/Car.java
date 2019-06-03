package com.app.Rentacar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.app.Rentacar.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(unique = true, updatable = false)
	private Integer id;
	
	@Column(nullable = false)
    private String carPlate;
	
	@Column(nullable = false)
    private String registrationYear;

	@Enumerated(EnumType.STRING)
	private StateEnum state;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created_car", columnDefinition = "DATETIME")
	private Date dateCreatedCar;
	
	@ManyToMany (mappedBy = "cars", fetch = FetchType.LAZY)
	private List<Rate> rates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
	private List<Rent> rents = new ArrayList<Rent>();
}
