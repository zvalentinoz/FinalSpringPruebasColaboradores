package com.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "tipos_usuarios")
@Entity
public class TipoUsuario {

	@Id
	@Column(name = "id_tipo")
	 private Integer idTipo;
	
	@Column(name = "descripcion")
	private String descripcion;
}
