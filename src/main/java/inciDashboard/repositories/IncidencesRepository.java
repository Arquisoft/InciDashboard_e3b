package inciDashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;

public interface IncidencesRepository extends CrudRepository<Incidence,Long> {

	@Query("SELECT i FROM Incidence i WHERE i.operario=?1 ")
	public List<Incidence> getIncidenciasForOperario(Operario operario);
	
	@Query("UPDATE Incidence i SET i.status = inciDashboard.util.Estado.ABIERTA WHERE i=?1 ")
	public void cambiaEstadoIncidenciaAAbierta(Incidence incidencia);
	
	@Query("UPDATE Incidence i SET i.status = inciDashboard.util.Estado.EN_PROCESO WHERE i=?1 ")
	public void cambiaEstadoIncidenciaAEnProceso(Incidence incidencia);
	
	@Query("UPDATE Incidence i SET i.status = inciDashboard.util.Estado.CERRADA WHERE i=?1 ")
	public void cambiaEstadoIncidenciaACerrada(Incidence incidencia);
	
	@Query("UPDATE Incidence i SET i.status = inciDashboard.util.Estado.ANULADA WHERE i=?1 ")
	public void cambiaEstadoIncidenciaAAnulada(Incidence incidencia);

	@Query("SELECT i FROM Incidence i WHERE i.operario=?1")
	public List<Incidence> getIncidenciasPeligrosasForOperario(Operario operario);
		
	
}
