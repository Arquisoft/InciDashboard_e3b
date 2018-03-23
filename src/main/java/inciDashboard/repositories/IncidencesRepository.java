package inciDashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;

public interface IncidencesRepository extends CrudRepository<Incidence,Long> {

	@Query("SELECT o.incidencias FROM Operario o WHERE o=?1 ")
	public List<Incidence> getIncidenciasForOperario(Operario operario);
		
	
}
