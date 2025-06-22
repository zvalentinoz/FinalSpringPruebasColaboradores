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

@Entity
@Table(name="productos")
@Getter
@Setter
public class Producto {
	
	@Id
	
	@Column(name="id_producto")
	private int idproducto;
	
	@Column(name="nombre_producto")
	private String nombreproducto;
	
	 @JoinColumn(name = "idtalla" , nullable = false)
	    @ManyToOne(fetch = FetchType.LAZY)
	    private Colegio idcolegio;

}
