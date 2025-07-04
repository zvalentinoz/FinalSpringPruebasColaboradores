package com.proyecto.controllers;

import java.util.List;
import java.util.Optional; // Necesario para buscar por ID si el servicio lo devuelve

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
import com.proyecto.service.ColegioService; // Posiblemente necesites este servicio si usas Colegio en editar
import com.proyecto.utils.Alert;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/compras")
public class ComprasController {

	// Inyección de dependencias
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private TallaService tallaService;
	
	@Autowired 
	private RopaService ropaService;
	
	@Autowired
	private ProveedorService proveedorService;

    @Autowired // Posiblemente necesites inyectar ColegioService
    private ColegioService colegioService; 
	
	@GetMapping("/listado")
	public String listado(Model model , HttpSession session) {
		// Verificación si existe un usuario logeado en la sesión
		if(session.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		
		// Carga la lista de ventas desde la BD
		List<Compra> listaCompra = compraService.listarTodas(); 
		model.addAttribute("ltCompra", listaCompra);
	
		return "compras/listado";  //listado.html
	}
	
	// Búsqueda por filtro en la barra 
	@GetMapping("/filtro")
	public String filtro(@ModelAttribute CompraFiltros filtro , Model model) { 
		List<Compra> listaCompra = compraService.busqueda(filtro);
		model.addAttribute("tallas", tallaService.listarTodas() );
		model.addAttribute("ropas", ropaService.listarTodos());
		model.addAttribute("proveedores", proveedorService.listarTodo());
        model.addAttribute("colegios", colegioService.listarTodos()); // Asegúrate de cargar colegios también para el filtro
		model.addAttribute("filtro",filtro);
		model.addAttribute("listadoCompra", listaCompra);
		return "compras/filtro";   // plantilla filtro
	}
	
	
	// Método nuevo para la compra
	@GetMapping("/nuevo")
	public String nuevo(Model model) { 			
		model.addAttribute("tallas", tallaService.listarTodas() );
		model.addAttribute("ropas", ropaService.listarTodos());
		model.addAttribute("proveedores", proveedorService.listarTodo());
        model.addAttribute("colegios", colegioService.listarTodos()); // Agregado para el formulario "nuevo"
		model.addAttribute("compra", new Compra() );
		
		return "compras/nuevo"; // plantilla nuevo
	}
	
	// El registro de la compra que hacemos
	@PostMapping("/registrar")
	public String registrar(@Valid @ModelAttribute Compra compra ,
			BindingResult result , Model model , RedirectAttributes redirect) { 
		
		if(result.hasErrors()) { 
			model.addAttribute("tallas", tallaService.listarTodas() );
			model.addAttribute("ropas", ropaService.listarTodos());
			model.addAttribute("proveedores", proveedorService.listarTodo());
            model.addAttribute("colegios", colegioService.listarTodos()); // Agregado para manejar errores en "nuevo"
			model.addAttribute("alerta", Alert.sweetAlertInfo("Falta completar la información necesaria"));
			return "compras/nuevo";
		}
		
		RespuestaResultado respuesta = compraService.registrarCompra(compra);
		
		 if(!respuesta.success) { 
				model.addAttribute("tallas", tallaService.listarTodas() );
				model.addAttribute("ropas", ropaService.listarTodos());
				model.addAttribute("proveedores", proveedorService.listarTodo());
                model.addAttribute("colegios", colegioService.listarTodos()); // Agregado para manejar errores en "nuevo"
				model.addAttribute("alerta", Alert.sweetAlertError(respuesta.mensaje));
				return "compras/nuevo";
		 }
		 
		 // CAMBIO 1: Corregir "sucess" a "success" en Alert.sweetToast
		 String mensaje = Alert.sweetToast(respuesta.mensaje, "success" , 2000);
		 redirect.addFlashAttribute("mensaje", mensaje); // CAMBIO 2: Usar "mensaje" como nombre consistente			
		return "redirect:/compras/filtro"; // Se mantiene la redirección a filtro
	}
	
	// Editar la compra
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable String id , Model model) { 
		// CAMBIO 3: Cargar todos los datos necesarios para los selects del formulario de edición
		model.addAttribute("tallas", tallaService.listarTodas() );
		model.addAttribute("ropas", ropaService.listarTodos());
		model.addAttribute("proveedores", proveedorService.listarTodo());
        model.addAttribute("colegios", colegioService.listarTodos()); // Asegúrate de cargar colegios también
		
		Compra compra = compraService.buscarPorId(id);
		model.addAttribute("compra", compra);
		return "compras/editar";
	}
	
	// Guardar edición
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Compra compra , BindingResult result , Model model , 
			RedirectAttributes redirect) { 
		 // CAMBIO 4: Volver a cargar los datos para los SELECTs si hay errores de validación
		 if(result.hasErrors()) { 
				model.addAttribute("tallas", tallaService.listarTodas() );
				model.addAttribute("ropas", ropaService.listarTodos());
				model.addAttribute("proveedores", proveedorService.listarTodo());
                model.addAttribute("colegios", colegioService.listarTodos()); // Agregado para errores en "editar"
				model.addAttribute("alerta", Alert.sweetAlertInfo("Falta completar la información para la compra requerida"));
				return "compras/editar"; // Vuelve a la plantilla editar.html
		 }

		 // CAMBIO 5: Corregir "sucess" a "success" en Alert.sweetToast
		 RespuestaResultado respuesta = compraService.ActualizarCompra(compra);
		 
		 // CAMBIO 6: Volver a cargar los datos para los SELECTs si hay errores lógicos
		 if(!respuesta.success) { 
				model.addAttribute("tallas", tallaService.listarTodas() );
				model.addAttribute("ropas", ropaService.listarTodos());
				model.addAttribute("proveedores", proveedorService.listarTodo());
                model.addAttribute("colegios", colegioService.listarTodos()); // Agregado para errores en "editar"
				model.addAttribute("alerta", Alert.sweetAlertError(respuesta.mensaje));
				return "compras/editar";  // Vuelve a la plantilla editar.html
		 }
		 
		 // CAMBIO 7: Usar "mensaje" como nombre consistente para el flash attribute
		 String mensaje = Alert.sweetToast(respuesta.mensaje, "success" , 3000);
		 redirect.addFlashAttribute("mensaje", mensaje);			
		
		// CAMBIO 8: Redirigir al listado principal para ver los cambios actualizados.
		// Si quieres que vaya a 'filtro' déjalo como estaba. Si quieres al listado general, cámbialo.
		return "redirect:/compras/listado";  
	}
	
	
	@PostMapping("/cambiar-estado/{id}")
	public String cambiarEstado(@PathVariable String id, RedirectAttributes redirect) { // CAMBIO 9: Usar 'redirect' para consistencia

		RespuestaResultado respuesta = compraService.cambiarEstado(id);
		
		// CAMBIO 10: Usar "mensaje" como nombre consistente para el flash attribute
		// Asegúrate de que Alert.sweetToast devuelve el HTML del toast.
		String toast = Alert.sweetToast(respuesta.mensaje, "success", 5000);
		redirect.addFlashAttribute("mensaje", toast); 
		return "redirect:/compras/listado"; // La redirección al listado ya era correcta aquí.
	}
}