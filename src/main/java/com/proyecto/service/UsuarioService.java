package com.proyecto.service;

import java.util.List;



import com.proyecto.models.Usuario;


public interface UsuarioService {
  Usuario login (String nombre , String clave);
  List<Usuario> listarTodos();
}
