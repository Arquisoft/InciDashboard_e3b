package inciDashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;
import inciDashboard.repositories.IncidencesRepository;
import inciDashboard.util.Estado;

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
	
	public void cambiaEstadoIncidencia(Incidence inci,Estado es) {
		switch (es) {
        case ABIERTA:  
        	incidencesRepository.cambiaEstadoIncidenciaAAbierta(inci);
        	break;
        case EN_PROCESO:  
        	incidencesRepository.cambiaEstadoIncidenciaAEnProceso(inci);
        	break;
        case CERRADA:  
        	incidencesRepository.cambiaEstadoIncidenciaACerrada(inci);
        	break;
        case ANULADA:  
        	incidencesRepository.cambiaEstadoIncidenciaAAnulada(inci);
        	break;
		default:
			break;	
		}
		
	}

}
