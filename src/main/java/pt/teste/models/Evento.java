package pt.teste.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	private LocalDate startLocalDate;
	private LocalTime startLocalTime;
	private LocalDate endLocalDate;
	private LocalTime endLocalTime;
	private String description;
	private TypeEvento type;
	private ArrayList<Atribute> atributes;
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public LocalDate getDateStart(){
		return this.startLocalDate;
	}
	
	public LocalDate getDateEnd(){
		return this.endLocalDate;
	}
	
	public String getTimeStart(){
		return this.startLocalTime.toString();
	}
	
	public String getTimeEnd(){
		return this.endLocalTime.toString();
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
		this.setStartLocalDate(date_start.toLocalDate());
		this.setStartLocalTime(date_start.toLocalTime());
	}
	
	public void setDateEnd(LocalDateTime date_end){
		this.setEndLocalDate(date_end.toLocalDate());
		this.setEndLocalTime(date_end.toLocalTime());
	}
	
	public void setStartLocalDate(LocalDate startLocalDate) {
		this.startLocalDate = startLocalDate;
	}

	public void setStartLocalTime(LocalTime startLocalTime) {
		this.startLocalTime = startLocalTime;
	}
	
	public void setTimeStart(String startLocalTime) {
		this.startLocalTime = LocalTime.parse(startLocalTime);
	}

	public void setEndLocalDate(LocalDate endLocalDate) {
		this.endLocalDate = endLocalDate;
	}

	public void setEndLocalTime(LocalTime endLocalTime) {
		this.endLocalTime = endLocalTime;
	}
	
	public void setTimeEnd(String endLocalTime) {
		this.endLocalTime = LocalTime.parse(endLocalTime);
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
	
	public static boolean createEvento(Evento evento) {
		
		try {
			String params = URLEncoder.encode(evento.getName(), "UTF-8") + "," +
					URLEncoder.encode(evento.getDateStart().toString() + " " + evento.getTimeStart() + ":00", "UTF-8") + "," +
					URLEncoder.encode(evento.getDateEnd().toString() + " " + evento.getTimeEnd() + ":00", "UTF-8") + "," +
					URLEncoder.encode(evento.getDescription(), "UTF-8") + "," +
					URLEncoder.encode("1", "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(evento.getTypeObj().getId()), "UTF-8");
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
	
	public static boolean deleteEvento(Evento evento) {
		try {
    		URL url = new URL(Constants.rootURL + DELETE_URL + evento.getId());
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
	
	public static boolean editEvento(Evento evento) {
		
		try {
			String params = URLEncoder.encode(String.valueOf(evento.getId()), "UTF-8") + "," +
					URLEncoder.encode(evento.getName(), "UTF-8") + "," +
					URLEncoder.encode(evento.getDateStart().toString() + " " + evento.getTimeStart() + ":00", "UTF-8") + "," +
					URLEncoder.encode(evento.getDateEnd().toString() + " " + evento.getTimeEnd() + ":00", "UTF-8") + "," +
					URLEncoder.encode(evento.getDescription(), "UTF-8") + "," +
					URLEncoder.encode("1", "UTF-8") + "," +
					URLEncoder.encode(String.valueOf(evento.getTypeObj().getId()), "UTF-8");
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
