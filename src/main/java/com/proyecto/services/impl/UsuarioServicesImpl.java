package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Usuario;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.service.UsuarioService;
@Service
public class UsuarioServicesImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public Usuario login(String nombre, String clave) {

		return usuarioRepo.findByNombreAndClave(nombre, clave);
	}

	@Override
	public List<Usuario> listarTodos() {

		return usuarioRepo.findAll();
	}

}
