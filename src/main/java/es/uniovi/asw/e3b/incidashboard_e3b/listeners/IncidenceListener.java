package es.uniovi.asw.e3b.incidashboard_e3b.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import es.uniovi.asw.e3b.incidashboard_e3b.controllers.IncidenciasController;
import es.uniovi.asw.e3b.incidashboard_e3b.controllers.MainController;
import es.uniovi.asw.e3b.incidashboard_e3b.entities.Incidence;
import es.uniovi.asw.e3b.incidashboard_e3b.util.ParserJsonToIncidence;

@ManagedBean
public class IncidenceListener {

	private static final Logger logger = Logger.getLogger(IncidenceListener.class);

	@Value("${kafka.topic:incidences}")
	private String kafkaTopic;

	@Autowired
	private MainController mainController;

	@KafkaListener(topics = "${kafka.topic:incidences}")
	public void listen(String data) {
		logger.info("New incidence received: \"" + data + "\" on topic '" + kafkaTopic + "'");

		mainController.sendNewMessage(data);
		// Recoger incidencia recibida y tratarla // comentado todo... comprobar si es
		// correcto, sino: eliminar comentario
		Incidence incidencia = ParserJsonToIncidence.JsonToIncidence(new JSONObject(data));
		IncidenciasController inciController = new IncidenciasController();
		inciController.recieveIncidence(incidencia);
	}

}