package inciDashboard.repositories;

import org.springframework.data.repository.CrudRepository;


import inciDashboard.entities.Incidence;

public interface IncidencesRepository extends CrudRepository<Incidence,Long> {

}
