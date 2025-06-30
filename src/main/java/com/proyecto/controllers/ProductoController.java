package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.LoginDTO.ProductoForm;
import com.proyecto.models.Producto;
import com.proyecto.service.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listado-productos")
	public String mostrarListado(Model model) { 
		 List<Producto> listaProducto = productoService.listarTodos();
		model.addAttribute("listaProducto", listaProducto);
		return "listado"; // plantilla listado.html 
	}
	
	//mostrar formulario de producto
	@GetMapping("/nuevo")
	public String mostrarFormularioProducot(Model model) { 
		model.addAttribute("productoForm", new ProductoForm());
		return "producto_form"; // definir HTMl esto
	}
	
	// procesar registro de producto
	@PostMapping("/registrar")
	public String registrarProducto(@Valid @ModelAttribute("productoForm") ProductoForm productoForm , 
			BindingResult result , 
			Model model) { 
		 if(result.hasErrors()) { 
			 return "producto_form";
		 }
		 Producto nuevo = new Producto();
		  nuevo.setNombre(productoForm.getNombreProducto());
		  productoService.registrar(nuevo);
		  return "redirect:/productos/listado"; //HTML listado
		 		
	}
	
}
