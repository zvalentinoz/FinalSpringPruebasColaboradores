package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.models.Colegio;

public interface ColegioRepository extends JpaRepository<Colegio, Integer> {

}
