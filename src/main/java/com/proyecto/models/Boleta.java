package com.proyecto.models;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "boleta")
public class Boleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "numero_boleta")
	private int numeroBoleta;
	
	@Column(name = "fecha_registro")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;
	
	@JoinColumn(name= "codigo_usuario" , nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@OneToMany(mappedBy = "boleta" , cascade = CascadeType.ALL)
	private List<DetalleBoleta> listaBoleta;
	
	public Double getTotal() { 
		return listaBoleta.stream().mapToDouble(b -> b.getPrecio() * b.getCantidad() ).sum();
	}
	
}
