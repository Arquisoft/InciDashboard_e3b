package inciDashboard.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import inciDashboard.entities.Incidence;
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
		List<Incidence> incidencias = incidenciasService.getIncidenciasForOperario(operario);
		if(incidenciasService.getIncidenciasPeligrosasForOperario(operario).size()>0)
		{
			model.addAttribute("errorMsg", "Hay incidencias peligrosas que chequear");
		}
		
		model.addAttribute("incidenciasList", incidencias);		
		return "incidencias/listaIncidencias";
	}
	
	@RequestMapping("/incidencias/listPeligrosas")
	public String getListPeligrosas(Model model, Principal principal) {
		String email = principal.getName(); // Email es el name de la autenticación		
		Operario operario = operariosService.getOperarioByEmail(email);		
		List<Incidence> incidencias = incidenciasService.getIncidenciasPeligrosasForOperario(operario);
		model.addAttribute("incidenciasList", incidencias);		
		return "incidencias/listaIncidencias";
	}
	
	
	
	
	
	@RequestMapping("/incidencias/locationOf/{id}")
	public String getLocationInci(Model model, @PathVariable Long id) {
		Incidence incidencia = incidenciasService.findById(id);
		if (incidencia != null) {
			model.addAttribute("incidencia", incidencia);
			String[] cadena=incidencia.getLocation().split(",");
			model.addAttribute("latitud",Double.parseDouble(cadena[0]));
			model.addAttribute("longitud",Double.parseDouble(cadena[1]));
			return "incidencias/mapping";
		}
		return "redirect:/incidencias/list";
	}
	
	/**
	 * Método que recibe incidencias, las mete en la base de datos y actualiza la tabla
	 * con el listado de todas las incidencias
	 * @param incidencia de la incidencia recivida de tipo Long
	 */
	public void recieveIncidence(Incidence incidencia) {
		//asignar incidencia
		//meter incidencia en base de datos
		//incidenciasService.addIncidencia(in);
		//actualizar tabla
	}

}
