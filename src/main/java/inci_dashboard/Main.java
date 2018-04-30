package inci_dashboard;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import inci_dashboard.entities.Incidence;
import inci_dashboard.util.ParserJsonToIncidence;

/**
 * Clase que inicia el compilador e invoca a todas sus fases.
 * 
 * No es necesario modificar este fichero. En su lugar hay que modificar:
 * - Para An�lisis Sint�ctico: 'sintactico/sintac.y' y 'sintactico/lexico.l'
 * - Para An�lisis Sem�ntico: 'semantico/Identificacion.java' y 'semantico/ComprobacionDeTipos.java'
 * - Para Generaci�n de C�digo: 'generacionDeCodigo/GestionDeMemoria.java' y 'generacionDeCodigo/SeleccionDeInstrucciones.java'
 *
 * @author Ra�l Izquierdo
 * 
 */
public class Main {
	public static final String json = "src/main/java/inci_dashboard/example-incidence.json";	

	public static void main(String[] args) throws Exception {
		
		File file = new File(json);
	    String content = FileUtils.readFileToString(file, "utf-8");
	    
	    // Convert JSON string to JSONObject
	    JSONObject jsonObject = new JSONObject(content);   
		Incidence inci = ParserJsonToIncidence.JsonToIncidence(jsonObject);
		if(inci!=null)
			System.out.println(inci.getIncidenceName());
		 

		
	}

	
}
