package com.proyecto.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="ropa")
@Getter
@Setter

public class Ropa {
	@Id
	@Column(name = "id_ropa")
	 private int  idRopa;
	 
	@Column(name = "nombre_ropa")
	 private String nombreRopa;
	 
	@Column(name = "img_ropa")
	private String imgRopa;
	
}
