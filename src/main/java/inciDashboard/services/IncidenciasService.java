package inciDashboard.services;

import java.util.List;

import org.springframework.stereotype.Service;

import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;

@Service
public class IncidenciasService {
	
	public List<Incidence> getIncidenciasForOperario(Operario operario) {
		return getIncidenciasForOperario(operario);
	}

}
