package com.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbusuario")
@Getter
@Setter
public class Usuario {

	@Id
	
	@Column(name="CODIGO")
	private int codigo;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="CLAVE")
	private int clave;
}
