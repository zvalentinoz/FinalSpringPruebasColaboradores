package com.proyecto.datos;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class DetalleBoletaId {
	private Integer boleta;
	private String compra;
	
	@Override
	public boolean equals(Object o) { // validadcion del objecto si su copia es diferente 
		if (this == o) return true;
		if (!(o instanceof DetalleBoletaId)) return false;
		DetalleBoletaId that = (DetalleBoletaId) o;
		return Objects.equals(boleta, that.boleta) &&
		       Objects.equals(compra, that.compra);
	}

	@Override
	public int hashCode() { //recuperacion en forma de bits
		return Objects.hash(boleta, compra);
	}
	
}
