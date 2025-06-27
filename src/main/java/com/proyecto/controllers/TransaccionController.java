package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.models.Transaccion;
import com.proyecto.service.TransaccionService;

@Controller
public class TransaccionController {

	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping("/Transacciones")
	public String mostrarTransacciones(Model model) { 
	 List<Transaccion> listTransaccion = transaccionService.listarTodas();
	 model.addAttribute("listaTransaccion", listTransaccion);
	 return "transaccion"; //plantillas Transaccion
	}
	
}
