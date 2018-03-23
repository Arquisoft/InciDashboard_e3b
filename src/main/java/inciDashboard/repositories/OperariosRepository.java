package inciDashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Operario;

public interface OperariosRepository extends CrudRepository<Operario,Long> {
	
	Operario findByEmail(String email);
}
