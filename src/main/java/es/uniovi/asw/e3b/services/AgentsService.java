package inci_dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inci_dashboard.entities.Agent;
import inci_dashboard.repositories.AgentsRepository;

@Service
public class AgentsService {
	
	@Autowired
	AgentsRepository agentsRepository;
	
	public void addAgent(Agent agent) {
		agentsRepository.save(agent);
	}

}
