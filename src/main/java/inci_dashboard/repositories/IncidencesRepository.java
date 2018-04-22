package inci_dashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import inci_dashboard.entities.Incidence;
import inci_dashboard.entities.Operario;

public interface IncidencesRepository extends CrudRepository<Incidence,Long> {

	@Query("SELECT i FROM Incidence i WHERE i.operario=?1 ")
	public List<Incidence> getIncidenciasForOperario(Operario operario);
	
	  
	
}
