package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.models.Producto;


public interface ProductoRepository  extends JpaRepository<Producto, Integer>{

}
