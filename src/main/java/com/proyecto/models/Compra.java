package com.proyecto.models;


import java.time.LocalDate;
import java.util.Date;



import java.time.LocalDate;

import org.hibernate.annotations.CollectionIdMutability;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="compra")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Compra {

	@Id
    @Column(name="id_compra")
	@Pattern(regexp = "[CP][0-9]{5}" , message = "El codigo de compra no es correcto su formato")
    private String idCompra;
	
	 @Column(name="fecha_Registro" , nullable = false)
	 @DateTimeFormat(pattern = "yyyy-MM-dd" )
	 private LocalDate fechaRegistro;

    @Column(name="cantidad")
    @NotNull(message = "la cantidad es requerida")
    @PositiveOrZero(message = "la cantidad no puede ser negativo -1")
    private Integer cantidad;

    @Column(name="precio_unitario" , nullable = false)
    @NotNull(message = "el precio es requerido")
    @Positive(message = "el precio debe ser mayor a 0")
    private Double preciounitario;

    @Column(name="precio_total", insertable = false, updatable = false)
    private Double preciototal;

	@Column(name="estado")
    private Boolean estado;
    
 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor" )
    private Proveedor idProveedor;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ropa" )
    private Ropa idRopa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talla" )
    private Talla idTalla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_colegio" )
    private Colegio idColegio;


  
	
}
