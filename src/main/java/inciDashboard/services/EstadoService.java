package inciDashboard.services;

import org.springframework.stereotype.Service;

import inciDashboard.util.Estado;

@Service
public class EstadoService {

	public Estado[] getEstados() {
		return Estado.values();
	}
	
}
