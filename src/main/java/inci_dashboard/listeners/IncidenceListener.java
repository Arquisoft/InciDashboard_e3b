package inci_dashboard.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import inci_dashboard.controllers.IncidenciasController;
import inci_dashboard.controllers.MainController;
import inci_dashboard.entities.Incidence;
import inci_dashboard.util.ParserJsonToIncidence;


@ManagedBean
public class IncidenceListener {

    private static final Logger logger = Logger.getLogger(IncidenceListener.class);
    
    @Autowired
    private MainController mainController;

    @KafkaListener(topics = "incidences")
    public void listen(String data) {
    	mainController.sendNewMessage(data);
        logger.info("New incidence received: \"" + data + "\"");

        // Recoger incidencia recibida y tratarla // comentado todo... comprobar si es correcto, sino: eliminar comentario
        Incidence incidencia = ParserJsonToIncidence.JsonToIncidence(new JSONObject(data));
        IncidenciasController inciController = new IncidenciasController();
        inciController.recieveIncidence(incidencia);
    }



}