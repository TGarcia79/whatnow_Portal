package pt.teste.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import pt.teste.testeapp.Constants;

public class Evento {
	
	private final static String LIST_URL = "/v1/event/list";
	private final static String CREATE_URL = "/v1/event/create?Event=";
	private final static String DELETE_URL = "/v1/event/delete?EventId=";
	private final static String EDIT_URL = "/v1/event/edit?Event=";
	private final static String ATRIBUTE_CREATE_URL = "/v1/event/atribute/create?Event=";
	private final static String ATRIBUTE_DELETE_URL = "/v1/event/atribute/delete?EventId=";
	private final static String ATRIBUTE_EDIT_URL = "/v1/event/atribute/edit?Event=";
	
	private int id;
	private String name;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private String description;
	private TypeEvento type;
	private ArrayList<Atribute> atributes;
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public LocalDateTime getDateStart(){
		return this.dateStart;
	}
	
	public LocalDateTime getDateEnd(){
		return this.dateEnd;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getType(){
		return this.type.getType();
	}
	
	public TypeEvento getTypeObj(){
		return this.type;
	}
	
	public ArrayList<Atribute> getAtributes(){
		return this.atributes;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDateStart(LocalDateTime date_start){
		this.dateStart = date_start;
	}
	
	public void setDateEnd(LocalDateTime date_end){
		this.dateEnd = date_end;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setType(TypeEvento type){
		this.type = type;
	}
	
	public void setAtributes(ArrayList<Atribute> atributes){
		this.atributes = atributes;
	}

public static ArrayList<Evento> getEventos(){
		
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    	
    	try {
    		URL url = new URL(Constants.rootURL + LIST_URL);
	    	HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String text = new Scanner(in).useDelimiter("\\A").next();
			//System.out.println(text);
			JsonObject json = Json.parse(text);
			JsonArray eventArray = json.getArray("Events");
			for(int i = 0; i < eventArray.length(); i++) {				
				JsonObject event = eventArray.getObject(i);
				
				Evento evento = new Evento();
		        TypeEvento typeEvento = new TypeEvento();
		        ArrayList<Atribute> atributes = new ArrayList<Atribute>();
		        
		        evento.setId((int)event.getNumber("Id"));
		        evento.setName(event.getString("Name"));
		        String dateTemp = event.getString("Date_Start");		        
		        evento.setDateStart(LocalDateTime.parse(dateTemp.substring(0, dateTemp.length()-1)));
		        dateTemp = event.getString("Date_End");
		        evento.setDateEnd(LocalDateTime.parse(dateTemp.substring(0, dateTemp.length()-1)));
		        evento.setDescription(event.getString("Description"));
		        JsonObject type = event.get("Type");
		        typeEvento.setId((int)type.getNumber("id"));
		        typeEvento.setType(type.getString("type"));
		        typeEvento.setDescription(type.getString("description"));
		        evento.setType(typeEvento);
		        JsonArray atributeArray = event.get("Atributes");
		        for(int j = 0; j < atributeArray.length(); j++) {
		        	JsonObject atribute = atributeArray.getObject(j);
		        	Atribute _atribute = new Atribute();
		        	_atribute.setId((int)atribute.getNumber("id"));
		        	_atribute.setType(atribute.getString("type"));
		        	_atribute.setDescription(atribute.getString("description"));
		        	
		        	atributes.add(_atribute);
		        }
		        evento.setAtributes(atributes);
		        
		        listaEventos.add(evento);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
    	
		return listaEventos;
	}
	
	public static boolean createCasa(Casa casa) {
		
		try {
			String params = URLEncoder.encode(casa.getName(), "UTF-8") + "," +
					URLEncoder.encode(casa.getCommercial_name(), "UTF-8") + "," +
					URLEncoder.encode(casa.getNif(), "UTF-8") + "," +
					URLEncoder.encode(casa.getMail(), "UTF-8") + "," +
					URLEncoder.encode(casa.getPhone(), "UTF-8") + "," +
					URLEncoder.encode(casa.getAddress(), "UTF-8") + "," +
					URLEncoder.encode(casa.getDescription(), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getLatitude()), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getLongitude()), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getTypeObj().getId()), "UTF-8") + "," +
					URLEncoder.encode(/*String.valueOf(casa.getUser().getId())*/"1", "UTF-8");
			String urlString = Constants.rootURL + CREATE_URL;
			URL url = new URL(urlString+params);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			con.setRequestMethod("POST");
			
			con.connect();
			int a = con.getResponseCode();
			System.out.println(a);
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean deleteCasa(Casa casa) {
		try {
    		URL url = new URL(Constants.rootURL + DELETE_URL +casa.getId());
    		HttpURLConnection con = (HttpURLConnection) url.openConnection();

    		con.setRequestMethod("POST");
			
    		con.connect();
    		int a = con.getResponseCode();
			System.out.println(a);
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean editCasa(Casa casa) {
		
		try {
			String params = URLEncoder.encode(String.valueOf(casa.getId()), "UTF-8") + "," +
					URLEncoder.encode(casa.getName(), "UTF-8") + "," +
					URLEncoder.encode(casa.getCommercial_name(), "UTF-8") + "," +
					URLEncoder.encode(casa.getNif(), "UTF-8") + "," +
					URLEncoder.encode(casa.getMail(), "UTF-8") + "," +
					URLEncoder.encode(casa.getPhone(), "UTF-8") + "," +
					URLEncoder.encode(casa.getAddress(), "UTF-8") + "," +
					URLEncoder.encode(casa.getDescription(), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getLatitude()), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getLongitude()), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getTypeObj().getId()), "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(casa.getUser().getId()), "UTF-8");
			String urlString = Constants.rootURL + EDIT_URL;
			URL url = new URL(urlString+params);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			con.setRequestMethod("POST");
			
			con.connect();
			int a = con.getResponseCode();
			System.out.println(a);
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
}
