package inciDashboard.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;


@ManagedBean
public class IncidenceListener {

    private static final Logger logger = Logger.getLogger(IncidenceListener.class);

    @KafkaListener(topics = "incidence")
    public void listen(String data) {
        logger.info("New incidence received: \"" + data + "\"");
        //recoger incidencia recibida y tratarla
    }



}