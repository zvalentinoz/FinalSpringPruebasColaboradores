package com.proyecto.models;

import com.proyecto.datos.DetalleBoletaId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="detalle_boleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DetalleBoletaId.class)
public class DetalleBoleta {

	@Id
	@ManyToOne
	@JoinColumn(name="numero_boleta" , referencedColumnName = "numero_boleta")
	private Boleta boleta;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_compra" , referencedColumnName = "id_compra")
	private Compra compra;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="precio_unitario")
	private Double precio;
	
}
