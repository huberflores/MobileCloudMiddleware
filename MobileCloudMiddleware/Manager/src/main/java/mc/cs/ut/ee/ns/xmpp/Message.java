package mc.cs.ut.ee.ns.xmpp;

import java.util.ArrayList;


public class Message {

	private ArrayList<Pair> attributes;
	
	public Message(){
		attributes = new ArrayList<Pair>();
		
	}
	
	public void addAttribute(String n, String v){
		attributes.add(new Pair(n,v));
	}
	
	public ArrayList getMessage(){
		return attributes;
	}
	

	
}
