package es.uniovi.asw.e3b.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import es.uniovi.asw.e3b.controllers.IncidenciasController;
import es.uniovi.asw.e3b.controllers.KafkaController;
import es.uniovi.asw.e3b.controllers.MainController;
import es.uniovi.asw.e3b.entities.Incidence;
import es.uniovi.asw.e3b.util.ParserJsonToIncidence;


@ManagedBean
public class IncidenceListener { // Consumer

    private static final Logger logger = Logger.getLogger(IncidenceListener.class);
    
    @Autowired
    private MainController mainController;

   
    @Autowired
    private KafkaController kafkaController;//contiene el ssemmitter
    
    
    
    @KafkaListener(topics = "incidences")
    public void listen(String data) {
    	mainController.sendNewMessage(data);
        logger.info("New incidence received: \"" + data + "\"");

        // Recoger incidencia recibida y tratarla // comentado todo... comprobar si es correcto, sino: eliminar comentario
        Incidence incidencia = ParserJsonToIncidence.JsonToIncidence(new JSONObject(data));
        IncidenciasController inciController = new IncidenciasController();
        inciController.recieveIncidence(incidencia); //incidencia persistente
        
        
        
        SseEventBuilder evento = SseEmitter.event().name("incidences").data(incidencia);
        kafkaController.sendData(evento);
    
    
    }



}