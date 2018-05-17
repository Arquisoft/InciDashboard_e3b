package es.uniovi.asw.e3b.incidashboard_e3b.services;

import org.springframework.stereotype.Service;

import es.uniovi.asw.e3b.incidashboard_e3b.util.Estado;

@Service
public class EstadoService {

	public Estado[] getEstados() {
		return Estado.values();
	}
	
	public Estado getEstado(String estado)
	{
		switch (estado) {
        case "ABIERTA":  
	         return Estado.ABIERTA;
        case "EN_PROCESO":  
        	return Estado.EN_PROCESO;
        case "CERRADA":  
        	return Estado.CERRADA;
        case "ANULADA":  
        	return Estado.ANULADA;
		default:
			return null;	
		
	}
	
}
}
