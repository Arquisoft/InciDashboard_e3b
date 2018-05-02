package inci_dashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import inci_dashboard.entities.Agent;

public interface AgentsRepository extends CrudRepository<Agent,Long>{

}
