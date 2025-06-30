package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.models.Transaccion;
import com.proyecto.service.TransaccionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
  //inyeccion de dependencia
	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping("/listado")
	public String listado(Model model , HttpSession session) {
		// verificacion si existe un usuario logeado en la session
	if(session.getAttribute("usuario") == null) {
		return "redirect:/login";
	}
	
	//carga la lista de ventas desde la BD
		List<Transaccion> listaTransaccion = transaccionService.listarTodas(); 
		model.addAttribute("ltTransaccion", listaTransaccion);
	
		return "listado";  //listado.html
	}
	 
	
}
