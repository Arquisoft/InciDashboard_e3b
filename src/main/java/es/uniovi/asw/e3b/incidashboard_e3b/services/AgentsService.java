package es.uniovi.asw.e3b.incidashboard_e3b.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.e3b.incidashboard_e3b.entities.Agent;
import es.uniovi.asw.e3b.incidashboard_e3b.repositories.AgentsRepository;

@Service
public class AgentsService {
	
	@Autowired
	AgentsRepository agentsRepository;
	
	public void addAgent(Agent agent) {
		agentsRepository.save(agent);
	}

}
