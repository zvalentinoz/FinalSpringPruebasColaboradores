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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="transacciones")
@DynamicInsert
@Getter
@Setter
public class Transaccion {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaccion")
    private Integer idtransaccion;

    @Column(name="codigo_cliente")
    private Integer codigocliente;

    @Column(name="nombre_cliente" , nullable = false)
    private String nombreCliente;

    @Column(name="apellido_cliente" , nullable = false)
    private String apellidoCliente;

    @Column(name="fecha_Registro" , nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto" )
    private Producto idproducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_talla" )
    private Talla idtalla;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_colegio" )
    private Colegio idcolegio;

    @Column(name="cantidad"  )
    private Integer cantidad;

    @Column(name="precio_unitario" , nullable = false)
    private Double preciounitario;

    @Column(name="precio_total" )
    private Double preciototal;

    
   


	@Column(name="estado" ,  nullable = false )
    private Boolean estado;
	
}
