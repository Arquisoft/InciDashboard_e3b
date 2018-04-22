package inci_dashboard.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import inci_dashboard.controllers.MainController;
import inci_dashboard.entities.Incidence;
import inci_dashboard.util.ParserJsonToIncidence;
import org.json.*;


@ManagedBean
public class IncidenceListener {

    private static final Logger logger = Logger.getLogger(IncidenceListener.class);
    
    @Autowired
    private MainController mainController;

    @KafkaListener(topics = "incidences")
    public void listen(String data) {
    	mainController.sendNewMessage(data);
        logger.info("New incidence received: \"" + data + "\"");
        //recoger incidencia recibida y tratarla
        Incidence incidencia = ParserJsonToIncidence.JsonToIncidence(new JSONObject(data));
//        IncidenciasController inciController = new IncidenciasController();
//        inciController.recieveIncidence(incidencia);
    }



}