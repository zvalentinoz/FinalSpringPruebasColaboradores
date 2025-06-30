package com.proyecto.LoginDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoForm {

	@NotBlank(message = "El nombre del producto es obligatorio")
	private String nombreProducto;

	
}
