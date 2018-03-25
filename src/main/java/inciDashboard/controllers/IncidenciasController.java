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
		model.addAttribute("incidenciasList", incidenciasService.getIncidenciasForOperario(operario));		
		return "incidencias/listaIncidencias";
	}
	
	
	@RequestMapping("/incidencias/locationOf/{id}")
	public String getLocationInci(Model model, @PathVariable Long id) {
		Incidence incidencia = incidenciasService.findById(id);
		if (incidencia != null) {
			model.addAttribute("incidencia", incidencia);
			return "incidencias/mapping";
		}
		return "redirect:/incidencias/list";
	}
	
	/**
	 * Método que recibe incidencias.
	 * Se encarga de seleccionar el operario con menos incidencias
	 * y asignarle la nueva incidencia recibida
	 * @param incidencia nueva que se acaba de recibir
	 */
	public void recieveIncidence(Incidence incidencia) {
		//asignar incidencia al operario con menos incidencias asignadas
		List<Operario> operarios = operariosService.findAll();
		operarios.sort((o1, o2) -> Integer.compare(o1.getIncidencias().size(), o2.getIncidencias().size()));
		if(operarios.size()>0) {
			incidencia.setOperario(operarios.get(0));
			operarios.get(0).añadirIncidencia(incidencia);
		}
		//meter incidencia en base de datos
		incidenciasService.addIncidencia(incidencia);
	}

}
