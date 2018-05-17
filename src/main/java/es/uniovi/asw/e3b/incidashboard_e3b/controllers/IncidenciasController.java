package es.uniovi.asw.e3b.incidashboard_e3b.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.e3b.incidashboard_e3b.entities.Incidence;
import es.uniovi.asw.e3b.incidashboard_e3b.entities.Operario;
import es.uniovi.asw.e3b.incidashboard_e3b.services.EstadoService;
import es.uniovi.asw.e3b.incidashboard_e3b.services.IncidenciasService;
import es.uniovi.asw.e3b.incidashboard_e3b.services.OperariosService;

@Controller
public class IncidenciasController {

	@Autowired
	EstadoService estadoService;

	@Autowired
	OperariosService operariosService;

	@Autowired
	IncidenciasService incidenciasService;

	@RequestMapping("/incidencias/list")
	public String getList(Model model, Principal principal) {
		String email = principal.getName(); // Email es el name de la autenticación
		Operario operario = operariosService.getOperarioByEmail(email);
		List<Incidence> incidencias = incidenciasService.getIncidenciasForOperario(operario);
		if (incidenciasService.getIncidenciasPeligrosasForOperario(operario).size() > 0) {
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
	public String getLocationInci(Model model, @PathVariable Long id, Principal principal) {
		Incidence incidencia = incidenciasService.findById(id);
		Operario operario = operariosService.getOperarioByEmail(principal.getName());
		if (operario.getIncidencias().contains(incidencia)) {

			if (incidencia != null) {
				model.addAttribute("estados", estadoService.getEstados());
				model.addAttribute("incidencia", incidencia);
				String[] cadena = incidencia.getLocation().split(",");
				model.addAttribute("latitud", Double.parseDouble(cadena[0]));
				model.addAttribute("longitud", Double.parseDouble(cadena[1]));
				return "incidencias/mapping";
			}
		}
		return "redirect:/incidencias/list";
	}

	@RequestMapping("/incidencias/list/update")
	public String updateList(Model model, Principal principal) {
		String email = principal.getName(); // Email es el name de la autenticación
		Operario operario = operariosService.getOperarioByEmail(email);
		List<Incidence> incidencias = incidenciasService.getIncidenciasForOperario(operario);

		model.addAttribute("incidenciasList", incidencias);
		return "incidencias/listaIncidencias :: tableIncidencias";
	}
	// /incidencia/update

	@RequestMapping(value = "/incidencias/update/{id}", method = RequestMethod.POST)
	public String getListActualizarIncidenciaFormulario(@PathVariable Long id, @RequestParam String estado,
			@RequestParam(required = false) String comentario) {
		Incidence i = incidenciasService.findOne(id);
		if (comentario != null && !comentario.isEmpty()) {
			incidenciasService.cambiaComentario(i, comentario);
		}
		incidenciasService.cambiaEstadoIncidencia(i, estadoService.getEstado(estado));

		return "redirect:/incidencias/list";
	}

	/**
	 * Método que recibe incidencias. Se encarga de seleccionar el operario con
	 * menos incidencias y asignarle la nueva incidencia recibida
	 * 
	 * @param incidencia
	 *            nueva que se acaba de recibir
	 */
	public void recieveIncidence(Incidence incidencia) {
		// asignar incidencia al operario con menos incidencias asignadas
		List<Operario> operarios = operariosService.findAll();
		if (operarios.size() > 0) {
			int op = 0;
			for (int i = 0; i < operarios.size(); i++) {
				if (operarios.get(i).getIncidencias().size() < operarios.get(op).getIncidencias().size())
					op = i;
			}
			incidencia.setOperario(operarios.get(op));
			operarios.get(op).añadirIncidencia(incidencia);
		}
		// meter incidencia en base de datos
		incidenciasService.addIncidencia(incidencia);
	}

}