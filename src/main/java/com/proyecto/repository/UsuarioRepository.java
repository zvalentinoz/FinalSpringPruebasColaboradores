package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   Usuario findByUsuarioAndContrasena(String usuario , String contrasena);
}
