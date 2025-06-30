package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.models.Colegio;
import com.proyecto.service.ColegioService;

@Controller
public class ColegioController {

	@Autowired
	private ColegioService servicio;
	
	@GetMapping("/listado-colegios")
	public String mostrarListadoColegios(Model model) { 
		List<Colegio> lstColegio = servicio.listarTodos();
		 model.addAttribute("lstColegio", lstColegio);
		 
		 return "colegios"; //retorna la plantilla colegios
	}
	
}
