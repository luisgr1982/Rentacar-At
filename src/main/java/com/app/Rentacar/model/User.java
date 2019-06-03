package com.app.Rentacar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(unique = true)
	private Integer id;
	
	@Column(nullable = false)
	private String dni;
	
	@Column(nullable = false)
	private String name;
		
	@Enumerated(EnumType.STRING)
	private StateEnum state;
 	
	@OneToMany(mappedBy = "user")
	private List<Rent> rents =new ArrayList<Rent>();

	@Temporal(TemporalType.TIMESTAMP)
 	@Column(name = "date_created_user", columnDefinition = "DATETIME")
	private Date dateCreatedUser;
}