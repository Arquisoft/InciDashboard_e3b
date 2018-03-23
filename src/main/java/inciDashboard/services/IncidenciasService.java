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
	IncidencesRepository incidencesRepository;
	
	public List<Incidence> getIncidenciasForOperario(Operario operario) {
		return incidencesRepository.getIncidenciasForOperario(operario);
	}

	public Incidence findById(Long id) {
		return incidencesRepository.findOne(id);
	}

}
