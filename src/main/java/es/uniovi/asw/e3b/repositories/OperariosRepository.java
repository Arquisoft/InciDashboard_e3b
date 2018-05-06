package es.uniovi.asw.e3b.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.e3b.entities.Operario;

public interface OperariosRepository extends CrudRepository<Operario,Long> {
	
	Operario findByEmail(String email);

	List<Operario> findAll();
	
}
