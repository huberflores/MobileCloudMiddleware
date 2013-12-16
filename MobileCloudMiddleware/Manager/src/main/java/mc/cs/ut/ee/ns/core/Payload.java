package mc.cs.ut.ee.ns.core;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;


public class Payload { 
	private ArrayList<Pair> attributes;
	private String jsonNotification;
	
	public Payload(){
		attributes = new ArrayList<Pair>();
		
	}
	
	public void addAttribute(String n, String v){
		attributes.add(new Pair(n,v));
	}
	
	private String jsonPayload() throws IOException{
		
		JSONObject jsonMessage = new JSONObject();
		Iterator i = attributes.iterator();
		Pair data;
		while (i.hasNext()){
			data = (Pair) i.next();
			jsonMessage.put(data.getPairName(), data.getPairValue());
		}   	
		
		StringWriter notification = new StringWriter();
		jsonMessage.writeJSONString(notification);
		jsonNotification = notification.toString();
	
		
		return jsonNotification;
		
	}
	
	public String getJsonNotification() throws IOException{
		return jsonPayload();
	}	
	
}
