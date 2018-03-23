package inciDashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Operario;
import inciDashboard.repositories.OperariosRepository;

@Service
public class OperariosService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OperariosRepository operatorsRepository;

	public void addOperador(Operario operador) {
		operador.setPassword(bCryptPasswordEncoder.encode(operador.getPassword()));
		operatorsRepository.save(operador);
	}
	
}
