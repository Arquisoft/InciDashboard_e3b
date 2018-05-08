package inci_dashboard.test;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import es.uniovi.asw.e3b.entities.Incidence;
import es.uniovi.asw.e3b.util.ParserJsonToIncidence;

public class ParserTest {

	@Test
	public void test() {
		String js = "{\r\n" + 
				"  \"username\": \"paco@gmail.com\",\r\n" + 
				"  \"password\": \"123456\",\r\n" + 
				"  \"kind\": \"Person\", \r\n" + 
				"  \"incidenceName\": \"Incidencia de prueba\",\r\n" + 
				"  \"description\": \"Descripción de la incidencia de prueba\",\r\n" + 
				"  \"location\": \"43.3582617,-5.8531647\",\r\n" + 
				"  \"labels\": [ \"prueba\", \"sensor\" ],\r\n" + 
				"  \"others\": [ \"file:///image.png\", \"file:///video.mkv\"],\r\n" + 
				"  \"fields\": { \"temperatura\": \"21\", \"humedad\": \"75\" },\r\n" + 
				"  \"status\": \"ABIERTA\",\r\n" + 
				"  \"comments\": [ \"Primer comentario\", \"Segundo Comentario\" ],\r\n" + 
				"  \"expiration\": \"2018-03-25T00:00:00+01:00\",\r\n" + 
				"  \"cacheable\": \"true\"\r\n" + 
				"}";
		Incidence inci = ParserJsonToIncidence.JsonToIncidence(new JSONObject(js));
		assertEquals(inci.getAgent().getEmail(),"paco@gmail.com");
		assertEquals(inci.getAgent().getPassword(),"123456");
		assertEquals(inci.getIncidenceName(),"Incidencia de prueba");
		assertEquals(inci.getDescription(), "Descripción de la incidencia de prueba");
		assertEquals(inci.getLocation(), "43.3582617,-5.8531647");
	}

}
