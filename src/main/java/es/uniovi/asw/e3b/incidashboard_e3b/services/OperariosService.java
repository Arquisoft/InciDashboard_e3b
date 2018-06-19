package es.uniovi.asw.e3b.incidashboard_e3b.services;

import es.uniovi.asw.e3b.incidashboard_e3b.entities.Incidence;
import es.uniovi.asw.e3b.incidashboard_e3b.entities.Operario;
import es.uniovi.asw.e3b.incidashboard_e3b.repositories.IncidencesRepository;
import es.uniovi.asw.e3b.incidashboard_e3b.repositories.OperariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperariosService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OperariosRepository operatorsRepository;

	@Autowired
	private IncidencesRepository incidencesRepository;

	@Autowired
	private IncidenciasService incidenciasService;

	public void addOperador(Operario operador) {
		operador.setPassword(bCryptPasswordEncoder.encode(operador.getPassword()));
		operatorsRepository.save(operador);
	}
	
	public Operario getOperarioByEmail(String email) {
		return operatorsRepository.findByEmail(email);		
	}

	public List<Operario> findAll() {
		return operatorsRepository.findAll();
	}


	/**
	 * Método que recibe incidencias. Se encarga de seleccionar el operario con
	 * menos incidencias y asignarle la nueva incidencia recibida
	 *
	 * @param incidencia
	 *            nueva que se acaba de recibir
	 */
	public void recieveIncidence(Incidence incidencia) {
		// asignar incidencia al operario con menos incidencias asignadas
		List<Operario> operarios = operatorsRepository.findAll();
		if (operarios.size() > 0) {
			int op = 0;
			for (int i = 0; i < operarios.size(); i++) {
				if (operarios.get(i).getIncidencias().size() < operarios.get(op).getIncidencias().size())
					op = i;
			}
			incidencia.setOperario(operarios.get(op));
			operarios.get(op).añadirIncidencia(incidencia);
		}
		// meter incidencia en base de datos
//		incidenciasService.addIncidencia(incidencia);
		incidencesRepository.save(incidencia);
		operatorsRepository.save(operarios);

	}

}
