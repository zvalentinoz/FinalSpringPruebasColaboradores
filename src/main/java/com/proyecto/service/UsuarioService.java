package com.proyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.datos.AutenticacionFiltrado;
import com.proyecto.models.Usuario;


public interface UsuarioService {
  Usuario login (AutenticacionFiltrado filter);
}
