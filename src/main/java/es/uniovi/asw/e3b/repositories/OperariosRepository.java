package inci_dashboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import inci_dashboard.entities.Operario;

public interface OperariosRepository extends CrudRepository<Operario,Long> {
	
	Operario findByEmail(String email);

	List<Operario> findAll();
	
}
