package com.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
public class Proveedor {
	@Id
	@Column(name="id_proveedor")
    private int idProveedor;
   
	@Column(name="nombre_proveedor")
    private String nombreProveedor;
   
	@Column(name="telefono")
    private String telefono;
}
