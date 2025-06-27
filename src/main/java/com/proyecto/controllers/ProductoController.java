package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.models.Producto;
import com.proyecto.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listado-productos")
	public String mostrarListado(Model model) { 
		 List<Producto> listaProducto = productoService.listarTodos();
		model.addAttribute("listaProducto", listaProducto);
		return "listado"; // plantilla listado.html 
	}
	
}
