package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.datos.AutenticacionFiltrado;
import com.proyecto.models.Usuario;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.service.UsuarioService;
@Service
public class AutenticacionService implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario login(AutenticacionFiltrado filter) {
		
		return  usuarioRepository.findByUsuarioAndContrasena(filter.getUsuario(), filter.getContrasena()) ;
	}
	
	



}
