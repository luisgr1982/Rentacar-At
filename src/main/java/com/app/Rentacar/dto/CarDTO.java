package com.app.Rentacar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CarDTO {
	private Integer id;
	
	@NotNull(message = "{car.carplate.notnull}")
	@NotBlank(message = "{car.carplate.notblank}")	
    private String carPlate;
	
	@NotNull(message = "{car.registrationyear.notnull}")
	@NotBlank(message = "{car.registrationyear.notblank}")	
    private String registrationYear;
}
