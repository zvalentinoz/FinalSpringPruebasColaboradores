package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.LoginDTO.TransaccionForm;
import com.proyecto.models.Transaccion;
import com.proyecto.service.ColegioService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.TallaService;
import com.proyecto.service.TransaccionService;

@Controller
@RequestMapping("/ventas")
public class TransaccionController {

	@Autowired
	private TransaccionService transaccionService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private TallaService tallaService;
	@Autowired
	private ColegioService colegioService;
	
	@GetMapping("/Transacciones")
	public String mostrarTransacciones(Model model) { 
	 List<Transaccion> listTransaccion = transaccionService.listarTodas();
	 model.addAttribute("listaTransaccion", listTransaccion);
	 return "transaccion"; //plantillas Transaccion
	}
	
	 //formulario nueva Transaccion
	
	@GetMapping("/nueva-venta")
	public String mostrarFormulario(Model model) { 
		model.addAttribute("transaccionForm", new TransaccionForm());
		model.addAttribute("productos", productoService.listarTodos());
		model.addAttribute("tallas", tallaService.listarTodas());
		model.addAttribute("colegios", colegioService.listarTodos());
		return "registrar_venta";
	}
	
	//procesar formulario
	
	@PostMapping("/registrar")
	public String registrarVenta(@ModelAttribute TransaccionForm transaccionForm , RedirectAttributes redirectAtributes) { 
		   Transaccion transaccion = new Transaccion();
		   transaccion.setCodigocliente(transaccionForm.getCodigoCliente());
		   transaccion.setNombreCliente(transaccionForm.getNombreCliente());
		   transaccion.setApellidoCliente(transaccionForm.getApellidoCliente());
		   transaccion.setCantidad(transaccionForm.getCantidad());
		   transaccion.setPreciounitario(transaccionForm.getPrecioUnitario());
		   transaccion.setEstado(true);
		   
		   transaccion.setIdproducto(productoService.listarTodos().stream() 
				   .filter(p -> p.getIdProducto().equals(transaccionForm.getIdProducto() ) )
				   .findFirst().orElse(null)); 
			transaccion.setIdtalla(tallaService.listarTodas().stream() 
					.filter(t -> t.getIdTalla().equals(transaccionForm.getIdTalla()))
					.findFirst().orElse(null)
					);
			transaccion.setIdcolegio(colegioService.listarTodos().stream() 
					.filter(c -> c.getIdColegio().equals(transaccionForm.getIdColegio()))
					.findFirst().orElse(null)
					);
		   //registra la venta o Transaccion
			transaccionService.registrarVenta(transaccion);
			
			redirectAtributes.addFlashAttribute("msg", "Venta Registrada Correctamente");
			
			return "redirect:/listado";
	}
	
	
}
