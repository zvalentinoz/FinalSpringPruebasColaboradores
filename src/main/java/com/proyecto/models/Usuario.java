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


@Getter
@Setter
@Entity
@Table(name = "tbusuario")

public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_usuario")
	private int codigoUsuario;
	
	@Column(name = "nombre_usuario", nullable = false)
	private String nombre;
  
	@Column(name = "apellido_usuario", nullable = false)
	private String apellido;
	
	@Column(name= "user_usuario", nullable = false)
	private String usuario;
	
	@Column(name= "contrasena_usuario" , nullable = false)
	private String contrasena;
	
	@ManyToOne()
	@JoinColumn(name = "id_tipo" , columnDefinition = "INT NOT NULL DEFAULT 2")
	private TipoUsuario tipoUsuario;
	
	@Column(name="estado" , columnDefinition =  "BIT NOT NULL DEFAULT 1" )
	private Boolean estado;

}
