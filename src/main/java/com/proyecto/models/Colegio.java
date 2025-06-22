package com.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="colegios")
@Getter @Setter
public class Colegio {

	@Id
	
	@Column(name="id_colegio")
	public int idcolegio;
	
	@Column(name="nombre_colegio")
	public String nombrecolegio;
	
}
