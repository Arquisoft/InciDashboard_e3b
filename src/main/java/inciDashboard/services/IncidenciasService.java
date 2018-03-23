package inciDashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;
import inciDashboard.repositories.IncidencesRepository;

@Service
public class IncidenciasService {
	
	
	@Autowired	
	private IncidencesRepository incidenciasR;
	
	public List<Incidence> getIncidenciasForOperario(Operario operario) {
		return incidenciasR.getIncidenciasForOperario(operario);
	}

	public Incidence findById(Long id) {
		return incidenciasR.findOne(id);
	}

}
