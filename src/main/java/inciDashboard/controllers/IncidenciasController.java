package inciDashboard.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import inciDashboard.entities.Operario;
import inciDashboard.services.IncidenciasService;
import inciDashboard.services.OperariosService;

@Controller
public class IncidenciasController {
	
	@Autowired
	OperariosService operariosService;
	
	@Autowired
	IncidenciasService incidenciasService;
	
	@RequestMapping("/incidencias/list")
	public String getList(Model model, Principal principal) {
		String email = principal.getName(); // Email es el name de la autenticación		
		Operario operario = operariosService.getOperarioByEmail(email);		
		model.addAttribute("incidenciasList", incidenciasService.getIncidenciasForOperario(operario));		
		return "incidencias/listaIncidencias";
	}

}
