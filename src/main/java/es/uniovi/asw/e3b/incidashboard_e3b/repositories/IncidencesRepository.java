package es.uniovi.asw.e3b.incidashboard_e3b.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.e3b.incidashboard_e3b.entities.Incidence;
import es.uniovi.asw.e3b.incidashboard_e3b.entities.Operario;

public interface IncidencesRepository extends CrudRepository<Incidence,Long> {

	@Query("SELECT i FROM Incidence i WHERE i.operario=?1 ")
	public List<Incidence> getIncidenciasForOperario(Operario operario);
	
	  
	
}
