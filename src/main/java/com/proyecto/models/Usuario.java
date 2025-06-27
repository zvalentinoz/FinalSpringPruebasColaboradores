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


@Getter
@Setter
@Entity
@Table(name = "tbusuario")

public class Usuario {
	
	@Id
	@Column(name = "codigo")
	private int codigo;
	
	@Column(name = "nombre")
	private String nombre;
  
	@Column(name = "clave")
	private String clave;
	
	@ManyToOne()
	@JoinColumn(name = "id_tipo")
	private TipoUsuario tipo;
	
	@Column(name="estado" )
	private Boolean estado;

}
