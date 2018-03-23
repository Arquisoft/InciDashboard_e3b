package inciDashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Operador;

public interface OperadoresRepository extends CrudRepository<Operador,Long> {
	
	Operador findByEmail(String username);
}
