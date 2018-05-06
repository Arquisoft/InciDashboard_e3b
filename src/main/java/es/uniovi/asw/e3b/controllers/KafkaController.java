package es.uniovi.asw.e3b.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@Controller
public class KafkaController {

	
private List<SseEmitter> emitter = Collections.synchronizedList(new ArrayList<>());
	
	public void sendData(SseEventBuilder event) {
		synchronized (this.emitter) {
			for (SseEmitter sseEmitter : this.emitter) {
				try {
					sseEmitter.send(event);
				} catch (IOException e) {
					sseEmitter = new SseEmitter(Long.MAX_VALUE);
					
				}
			}
		}
	}
	
	@CrossOrigin(origins = "http://localhost:8093") // manda respuesta a esta URL
	@RequestMapping("/getEmitter")
	public SseEmitter getEmitter() {
		return newEmitter();
	}

	public SseEmitter newEmitter() {
		
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // el timeout
		this.emitter.add(emitter);
		
		emitter.onCompletion(() -> this.emitter.remove(emitter));
		emitter.onTimeout(() -> this.emitter.remove(emitter));
		
		return emitter;
	}

	public List<SseEmitter> getSseEmitters() {
		return emitter;
	}

	
}
