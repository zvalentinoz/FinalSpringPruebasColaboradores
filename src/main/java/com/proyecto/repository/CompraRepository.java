package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.models.Compra;


public interface CompraRepository extends JpaRepository<Compra, String> {

	List<Compra> findAllByOrderByIdCompraDesc();
	
	@Query("""
			SELECT c FROM Compra c 
		     WHERE (:idProveedor IS NULL OR c.idProveedor.id = :idProveedor) 
		     AND (:idRopa IS NULL OR c.idRopa.id = :idRopa) 
		     AND (:idTalla IS NULL OR c.idTalla.id = :idTalla) 
		     AND (:idColegio IS NULL OR c.idColegio.id = :idColegio)
		     """)
		List<Compra> findAllWithFilters(@Param("idProveedor") Integer idProveedor,
		                               @Param("idRopa") Integer idRopa,
		                               @Param("idTalla") Integer idTalla,
		                               @Param("idColegio") Integer idColegio);
	
}
