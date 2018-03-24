package inciDashboard.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;


@ManagedBean
public class MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListener.class);

    @KafkaListener(topics = "incidence")
    public void listen(String data) {
        logger.info("New message received: \"" + data + "\"");
        //recoger incidencia recibida y tratarla
    }



}