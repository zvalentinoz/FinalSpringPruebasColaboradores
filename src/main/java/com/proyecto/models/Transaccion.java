package com.proyecto.models;

import java.time.LocalDate;
import java.util.Date;

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

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "transacciones")
public class Transaccion {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transaccion")
    private Integer idtransaccion;

    @JoinColumn(name = "codigo_cliente" , nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario codigocliente;

    @Column(name="nombre_cliente" , nullable = false)
    private String nombrecliente;

    @Column(name="apellido_cliente" , nullable = false)
    private String apellidocliente;

    @Column(name="fecha_Registro" , nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate fechaRegistro;

    @JoinColumn(name = "id_producto" , nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto idproducto;

    @JoinColumn(name = "id_talla" , nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Talla idtalla;

    @JoinColumn(name = "id_colegio" , nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Colegio idcolegio;

    @Column(name="cantidad"  )
    private Integer cantidad;

    @Column(name="precio_unitario" , nullable = false)
    private Double preciounitario;

    @Column(name="precio_total" )
    private Double preciototal;
    
    @Column(name="estado" ,  nullable = false )
    private Byte estado;
	
}
