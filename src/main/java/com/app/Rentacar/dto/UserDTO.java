package com.app.Rentacar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.app.Rentacar.tools.Dni;

import lombok.Data;

@Component
@Data
public class UserDTO {
	private Integer id;

	@NotNull(message = "{user.dniuser.notnull}")
	@NotBlank(message = "{user.dniuser.notblank}")
	@Size(max = 9, message = "{user.dniuser.size}")
	@Dni
	private String dni;

	@NotNull(message = "{user.nameuser.notnull}")
	@NotBlank(message = "{user.nameuser.notblank}")
	@Size(max = 150, message = "{user.nameuser.max}")
	private String name;
}
