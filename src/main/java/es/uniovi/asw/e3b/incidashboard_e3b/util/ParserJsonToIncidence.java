package es.uniovi.asw.e3b.incidashboard_e3b.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.uniovi.asw.e3b.incidashboard_e3b.entities.Agent;
import es.uniovi.asw.e3b.incidashboard_e3b.entities.Incidence;

public class ParserJsonToIncidence {
	
	public static Incidence JsonToIncidence(JSONObject json) {
		//El json real pasa un objeto agent por lo que no es tan facil obtener el username y password
		
		
		String username = json.getString("username");
		String password = json.getString("password");
		String kind = json.getString("kind");
		String incidenceName = json.getString("incidenceName");
		String descripcion = json.getString("description");
		String location = json.getString("location");
		
		Set<String> labels = new HashSet<String>();
		JSONArray jlabels = json.getJSONArray("labels");
		for(int i=0;i<jlabels.length();i++)
			labels.add((String) jlabels.get(i));
		
		Set<String> others = new HashSet<String>();
		JSONArray jothers = json.getJSONArray("others");
		for(int i=0;i<jothers.length();i++)
			others.add((String) jothers.get(i));				
		
		Set<String> comments = new HashSet<String>();
		JSONArray jcomments = json.getJSONArray("comments");
		for(int i=0;i<jcomments.length();i++)
			comments.add((String) jcomments.get(i));
		
		HashMap<String, String> fields = new HashMap<String,String>();
		JSONObject jfields = json.getJSONObject("fields");	
		fields = toMap(jfields);	

		
		Estado status = null;
		switch (json.getString("status")) {
        case "ABIERTA":  
        	status = Estado.ABIERTA;
        	break;
        case "EN_PROCESO":  
        	status = Estado.EN_PROCESO;
        	break;
        case "CERRADA":  
        	status = Estado.CERRADA;
        	break;
        case "ANULADA":  
        	status = Estado.ANULADA;
        	break;
		default:
			break;	
		}
		
		Boolean cacheable = json.getBoolean("cacheable");
		if(json.has("expiration")){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date expiration = new Date();
			try {
				expiration = formatter.parse(json.getString("expiration"));
			} catch (JSONException e) {			
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Agent agent = new Agent(username,password,location,username,"ident",kind);
		Incidence incidence = new Incidence(agent,incidenceName,descripcion,location,labels,comments,fields,status,expiration,cacheable,others,null);
		
		return incidence;		
	}
	
	public static HashMap<String, String> toMap(JSONObject object) throws JSONException {
		HashMap<String, String> map = new HashMap<String, String>();
	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);
	        value = toMap(new JSONObject(value));	        
	        map.put(key, value.toString());
	    }
	    return map;
	}
	


}
