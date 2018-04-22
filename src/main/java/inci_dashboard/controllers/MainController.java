package inci_dashboard.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    private List<SseEmitter> sseEmitters = new ArrayList<>();

    @RequestMapping("/")
    public String landing(Model model) {
        return "index";
    }

    public void sendNewMessage(String message) {
    	for (SseEmitter sseEmitter : sseEmitters) {
    		 try {
                 sseEmitter.send(message);
                 logger.info("Sending to client: " + message);
             } catch (Exception e) {
                 logger.info("Error on sending to client: " + message);
                 sseEmitter.complete();
             }
		}
    }
}