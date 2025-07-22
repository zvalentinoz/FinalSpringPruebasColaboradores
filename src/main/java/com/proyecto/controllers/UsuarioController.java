package com.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.proyecto.datos.AutenticacionFiltrado;
import com.proyecto.models.Usuario;
import com.proyecto.service.UsuarioService;
import com.proyecto.services.impl.AutenticacionService;
import com.proyecto.utils.Alert;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@Autowired
	private AutenticacionService autenticacionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping({"/" , "/login"})
	public String mostrarLogin(Model model) { 
		
	  	model.addAttribute("filter", new AutenticacionFiltrado()); // similar a AutenticacionFilter
	  	return "login";
	}
	
	@PostMapping("/iniciar-sesion")
	public String procesarLogin(@ModelAttribute AutenticacionFiltrado filter, HttpSession session, Model model,
			RedirectAttributes flash) { 
		 Usuario usuario = usuarioService.login(filter);
		 
		 if(usuario == null) { 
			 model.addAttribute("filter",  new AutenticacionFiltrado() );
			 model.addAttribute("alerta", Alert.sweetAlertError("Usuario o claves incorrectos"));
			 return "login";
		 }
		 String usuarioCompleto = String.format("%s %s",usuario.getNombre(), usuario.getApellido());
		 session.setAttribute("idUsuario", usuario.getCodigoUsuario());
		 session.setAttribute("usuarioCompleto", usuarioCompleto);
		 session.setAttribute("usuario", usuario.getUsuario());
		 
		 
		 String alerta = Alert.sweetImageUrl("Bienvenido a tienda", usuarioCompleto, "/img2/caralike.jpg");
		 flash.addFlashAttribute("alerta", alerta);
		 return "redirect:/compras/listado";
		 
	}
	
	@GetMapping("/logout")
     public String cerrarSession(HttpSession session) { 
		 session.invalidate();
		 return "redirect:/login";
		                                           
	}
	
}
