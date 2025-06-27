package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.models.Transaccion;


public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

}
