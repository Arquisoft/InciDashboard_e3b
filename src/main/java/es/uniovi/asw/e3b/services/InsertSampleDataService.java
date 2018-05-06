package es.uniovi.asw.e3b.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.e3b.entities.Agent;
import es.uniovi.asw.e3b.entities.Incidence;
import es.uniovi.asw.e3b.entities.Operario;

@Service
public class InsertSampleDataService {
	
	@Autowired
	OperariosService operariosService;
	
	@Autowired
	AgentsService agentsService;
	
	@Autowired
	IncidenciasService incidenciasService;
	
	@Autowired
	RolesService rolesService;
	
	
	@PostConstruct
	public void init() {
		Operario oper1 = new Operario( "oper12@gmail.es","123456","Operario");
		oper1.setRole(rolesService.getRoles()[0]);
		Agent ag1 = new Agent("Agent1","123456","Norte","agent121223221@gmail.com","Ag111223221","Person");
		Agent ag2 = new Agent("Agent2","123456","Sur","agent221223221@gmail.com","Ag221223221","Sensor");
		Agent ag3 = new Agent("Agent3","123456","Este","agent321223221@gmail.com","Ag331223221","Entity");
		agentsService.addAgent(ag1);
		agentsService.addAgent(ag2);
		agentsService.addAgent(ag3);
		Set<String> labels1 = new HashSet<String>();labels1.add("Labels de la incidencia 1");
		Set<String> labels2 = new HashSet<String>();labels2.add("Labels de la incidencia 2");
		Set<String> labels3 = new HashSet<String>();labels3.add("Labels de la incidencia 3");
		Set<String> labels4 = new HashSet<String>();labels4.add("Labels de la incidencia 4");
		Incidence inci1 = new Incidence(ag1,"Incidencia1","Descripcion1","21,33",labels1);
		Incidence inci2 = new Incidence(ag2,"Incidencia2","Descripcion2","60,43",labels2);
		Incidence inci3 = new Incidence(ag3,"Incidencia3","Descripcion3","-8,-99",labels3);
		Incidence inciPeligrosa = new Incidence(ag3,"Incidencia4","Peligro","-8,-99",labels4);
		inciPeligrosa.setOperario(oper1);
		inci1.setOperario(oper1);
		inci2.setOperario(oper1);
		inci3.setOperario(oper1);
		oper1.añadirIncidencia(inciPeligrosa);
		oper1.añadirIncidencia(inci1);
		oper1.añadirIncidencia(inci2);
		oper1.añadirIncidencia(inci3);
		operariosService.addOperador(oper1);		
	}
	
}
