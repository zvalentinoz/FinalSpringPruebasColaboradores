package com.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.LoginDTO.LoginDTO;
import com.proyecto.models.Usuario;
import com.proyecto.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping({"/" , "/login"})
	public String mostrarLogin(Model model) { 
		
	  	model.addAttribute("filter", new LoginDTO()); // similar a AutenticacionFilter
	  	return "login";
	}
	
	@PostMapping("/iniciar-sesion")
	public String procesarLogin(@ModelAttribute("filter") LoginDTO filter, 
			 HttpSession session , 
			 RedirectAttributes flash ,
			 Model model) { 
		 Usuario usuario = usuarioService.login(filter.getNombre(), filter.getClave());
		 
		 if(usuario == null) { 
			 model.addAttribute("alerta", "Usuario o claves incorrectos");
			 model.addAttribute("filter", new LoginDTO());
			 return "login";
		 }
		 session.setAttribute("usuario", usuario);
		 flash.addFlashAttribute("alerta", "Bienvenido" + usuario.getNombre() );
		 return "redirect:/listado";
		 
	}
	
	@GetMapping("/logout")
     public String cerrarSession(HttpSession session) { 
		 session.invalidate();
		 return "redirect:/login";
		
	}
	
}
