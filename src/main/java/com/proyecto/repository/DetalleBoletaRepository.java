package com.proyecto.repository;

import com.proyecto.models.DetalleBoleta;
import com.proyecto.datos.DetalleBoletaId; // Importar tu clase de clave compuesta
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, DetalleBoletaId> {
    // Este método buscará todos los DetalleBoleta por el idCompra
    // Asume que en tu modelo DetalleBoleta, la propiedad de tipo Compra se llama 'compra'
    // y que Compra tiene un 'idCompra' (String).
    List<DetalleBoleta> findByCompraIdCompra(String idCompra);
}