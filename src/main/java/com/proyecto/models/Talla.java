package com.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tallas")
@Getter
@Setter
public class Talla {
	@Id
	
	@Column(name="id_talla")
	private int idtalla;
	
	@Column(name="talla")
	private String talla;

}
