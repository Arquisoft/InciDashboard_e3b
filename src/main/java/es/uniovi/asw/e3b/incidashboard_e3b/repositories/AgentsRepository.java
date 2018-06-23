package es.uniovi.asw.e3b.incidashboard_e3b.repositories;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.e3b.incidashboard_e3b.entities.Agent;

public interface AgentsRepository extends CrudRepository<Agent,Long>{
  
  Agente findByEmail(String email);

}
