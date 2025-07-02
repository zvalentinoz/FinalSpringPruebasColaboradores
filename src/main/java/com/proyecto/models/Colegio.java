package com.proyecto.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "colegios")
public class Colegio {

	@Id
	@Column(name = "id_colegio")
	 private Integer  idColegio;
	 
	@Column(name = "nombre_colegio")
	 private String nombreColegio;

	
}


