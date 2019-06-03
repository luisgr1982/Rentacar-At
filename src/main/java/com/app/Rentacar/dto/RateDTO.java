package com.app.Rentacar.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RateDTO {
    private Integer id;
    
    @Digits(integer=10, fraction=2)
    private Double price;
    
    @NotNull(message = "{rate.date.start.notnull}")
	@NotBlank(message = "{rate.date.start.notblank}")	
    private String startDate;
    
    private String endDate;
}
