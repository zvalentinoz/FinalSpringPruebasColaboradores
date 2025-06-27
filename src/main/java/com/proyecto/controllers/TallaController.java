package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.models.Talla;
import com.proyecto.service.TallaService;

@Controller
public class TallaController {
 
	@Autowired
	private TallaService tallaService;
	
	@GetMapping("/listado-tallas")
		public String listadoTallas(Model model) { 
		 List<Talla> listaTalla = tallaService.listarTodas();
		 model.addAttribute("listaTalla", listaTalla);
		 
		 return "tallas"; // plantilla talla.html
	}
}
