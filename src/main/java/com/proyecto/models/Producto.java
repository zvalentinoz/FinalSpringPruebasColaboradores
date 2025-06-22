package com.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@Column(name = "id_producto")
	 private int  idProducto;
	 
	@Column(name = "nombre_producto")
	 private String nombre;
	
	   @JoinColumn(name = "idTalla" , nullable = false)
	   @ManyToOne(fetch = FetchType.LAZY)
	   private Talla idTalla;
	
}
