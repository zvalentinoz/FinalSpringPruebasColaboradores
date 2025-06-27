package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   Usuario findByNombreAndClave(String nombre , String clave);
}
