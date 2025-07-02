package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.datos.CompraFiltros;
import com.proyecto.datos.RespuestaResultado;
import com.proyecto.models.Compra;
import com.proyecto.service.CompraService;
import com.proyecto.service.ProveedorService;
import com.proyecto.service.RopaService;
import com.proyecto.service.TallaService;
import com.proyecto.utils.Alert;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/compras")
public class ComprasController {

	//inyeccion de dependencia
		@Autowired
		private CompraService compraService;
		
		@Autowired
		private TallaService tallaService;
		
		@Autowired 
		private RopaService ropaService;
		
		@Autowired
		private ProveedorService proveedorService;
		
		@GetMapping("/listado")
		public String listado(Model model , HttpSession session) {
			// verificacion si existe un usuario logeado en la session
		if(session.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		
		//carga la lista de ventas desde la BD
			List<Compra> listaCompra = compraService.listarTodas(); 
			model.addAttribute("ltCompra", listaCompra);
		
			return "compras/listado";  //listado.html
		}
		
		//busqueda por filtro en la barra 
		@GetMapping("/filtro")
		public String filtro(@ModelAttribute CompraFiltros filtro , Model model) { 
			List<Compra> listaCompra = compraService.busqueda(filtro);
			model.addAttribute("tallas", tallaService.listarTodas() );
			model.addAttribute("ropas", ropaService.listarTodos());
			model.addAttribute("proveedores", proveedorService.listarTodo());
			model.addAttribute("filtro",filtro);
			model.addAttribute("listadoCompra", listaCompra);
			return "compras/filtro";   // crear una plantilla filtro para esto
		}
		
		
		// metodo nuevo para la compra
		@GetMapping("/nuevo")
		public String nuevo(Model model) { 			
			model.addAttribute("tallas", tallaService.listarTodas() );
			model.addAttribute("ropas", ropaService.listarTodos());
			model.addAttribute("proveedores", proveedorService.listarTodo());
			model.addAttribute("compra", new Compra() );
			
			return "compras/nuevo"; // plantilla nuevo
		}
		
		// el registro de la compra que hacemos
		@PostMapping("/registrar")
		public String registrar(@Valid @ModelAttribute Compra compra ,
				BindingResult result , Model model , RedirectAttributes redirect) { 
			
			if(result.hasErrors()) { 
				model.addAttribute("tallas", tallaService.listarTodas() );
				model.addAttribute("ropas", ropaService.listarTodos());
				model.addAttribute("proveedores", proveedorService.listarTodo());
				model.addAttribute("alerta", Alert.sweetAlertInfo("Falta completar la informacion necesaria"));
				return "compras/nuevo";
			}
			
			RespuestaResultado respuesta = compraService.registrarCompra(compra);
			
			 if(!respuesta.success) { 
					model.addAttribute("tallas", tallaService.listarTodas() );
					model.addAttribute("ropas", ropaService.listarTodos());
					model.addAttribute("proveedores", proveedorService.listarTodo());
					model.addAttribute("alerta", Alert.sweetAlertError(respuesta.mensaje));
					return "compras/nuevo";
			 }
			 
			 String mensaje = Alert.sweetToast(respuesta.mensaje, "sucess" , 2000);
			 redirect.addFlashAttribute("mensaje", mensaje);			
			return "redirect:/compras/filtro";
		}
		
		//editar la compra
		@GetMapping("/editar/{id}")
		public String editar(@PathVariable String id , Model model) { 
			model.addAttribute("tallas", tallaService.listarTodas() );
			model.addAttribute("ropas", ropaService.listarTodos());
			model.addAttribute("proveedores", proveedorService.listarTodo());
			
			Compra compra = compraService.buscarPorId(id);
			model.addAttribute("compra", compra);
			return "compras/editar";
		}
		
		//guardar editar junto va
		@PostMapping("/guardar")
		public String guardar(@Valid @ModelAttribute Compra compra , BindingResult result , Model model , 
				RedirectAttributes redirect) { 
			 if(result.hasErrors()) { 
					model.addAttribute("tallas", tallaService.listarTodas() );
					model.addAttribute("ropas", ropaService.listarTodos());
					model.addAttribute("proveedores", proveedorService.listarTodo());
					model.addAttribute("alerta", Alert.sweetAlertInfo("Falta completar la informacion para la compra requerida"));
					return "compras/editar"; // plantilla llamda editar.html
			 }
			 RespuestaResultado respuesta = compraService.ActualizarCompra(compra);
			 
			 if(!respuesta.success) { 
					model.addAttribute("tallas", tallaService.listarTodas() );
					model.addAttribute("ropas", ropaService.listarTodos());
					model.addAttribute("proveedores", proveedorService.listarTodo());
					model.addAttribute("alerta", Alert.sweetAlertError(respuesta.mensaje));
					return "compras/editar";  // es lo mismo
			 }
			 
			 
			 String mensaje = Alert.sweetToast(respuesta.mensaje, "sucess" , 3000);
			 redirect.addFlashAttribute("mensaje", mensaje);			
			return "redirect:/compras/filtro";  // -> redirecciona ala url compras/filtro 
		}
}
