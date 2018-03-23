package inciDashboard.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Agent;
import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;
import inciDashboard.util.Estado;

@Service
public class InsertSampleDataService {
	
	@Autowired
	OperariosService operariosService;
	
	@PostConstruct
	public void init() {
		Operario oper1 = new Operario( "oper1@gmail.es","123456","Operario");
		
		Agent ag1 = new Agent("Agent1","123456","Norte","agent1@gmail.com","Ag1","Person");
		Agent ag2 = new Agent("Agent2","123456","Sur","agent2@gmail.com","Ag2","Sensor");
		Agent ag3 = new Agent("Agent3","123456","Este","agent3@gmail.com","Ag3","Entity");
		Incidence inci1 = new Incidence("Incidencia1",ag1,"Sudafrica",Estado.ABIERTA);
		Incidence inci2 = new Incidence("Incidencia2",ag2,"Sudafrica",Estado.ABIERTA);		
		Incidence inci3 = new Incidence("Incidencia3",ag3,"Sudafrica",Estado.ABIERTA);
		oper1.añadirIncidencia(inci1);
		oper1.añadirIncidencia(inci2);
		oper1.añadirIncidencia(inci3);
		operariosService.addOperador(oper1);		
	}
	
}
