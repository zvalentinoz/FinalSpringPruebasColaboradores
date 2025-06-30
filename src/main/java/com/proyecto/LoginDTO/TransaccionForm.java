package com.proyecto.LoginDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TransaccionForm {
	
	@NotBlank(message = "Codigo del cliente obligatorio")
	private Integer codigoCliente;
	
	@NotBlank(message = "Nombre obligatorio")
	private String nombreCliente;
	
	@NotBlank(message = "Apellido obligatario")
	private String apellidoCliente;
	
	@NotNull(message = "Seleccione un producto")
	private Integer idProducto;
	
	@NotNull(message = "Seleccion una talla")
	private Integer idTalla;
	
	@NotNull(message = "Seleccion un colegio")
	private Integer idColegio;
	
	@Min(value = 1 , message = "La cantidad debe ser mayor a 0")
	private Integer cantidad;
	
	@DecimalMin(value = "0.1" , message = "El precio debe ser mayor a 0" )
	private Double precioUnitario;
}
