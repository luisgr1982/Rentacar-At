package com.app.Rentacar.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data

public class RentDTO {
	
    private Integer id;
    
    private CarDTO car;
    
    private UserDTO client;

    @Digits(integer=10, fraction=2)
    private Double price;

    @NotNull(message = "{rent.car.date.start.notnull}")
	@NotBlank(message = "{rent.car.date.start.notblank}")	
    private String startDate;
    
    @NotNull(message = "{rent.car.date.start.notnull}")
	@NotBlank(message = "{rent.car.date.start.notblank}")	
    private String endDate;
}
