package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.models.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

}
