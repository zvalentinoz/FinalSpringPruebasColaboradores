package com.proyecto.datos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RopaFiltrosSeguridad {

	@NotEmpty(message = "Seleccione ")
	private String idRopa;
 
	@NotNull(message = "El precio es requerido")
	@Positive(message = "el precio debe ser mayor a 0 no -1")
	private double precio;
	
	@NotNull(message = "la cantidad es requerida")
	@Positive(message = "Minimo 1")
	private int cantidad;
	
	public Double getTotalRopa() { 
	  return precio * cantidad;	
	}
	
}
