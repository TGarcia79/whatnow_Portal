package pt.teste.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import pt.teste.testeapp.Constants;

public class TypeEvento {
	
	private final static String LIST_URL = "/v1/eventtype/list";
	private final static String CREATE_URL = "/v1/eventtype/create?EventType=";
	private final static String DELETE_URL = "/v1/eventtype/delete?EventTypeId=";
	private final static String EDIT_URL = "/v1/eventtype/edit?EventType=";
	
	private int id;
	private String type;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
public static ArrayList<TypeEvento> getTypesEvento(){
		
		ArrayList<TypeEvento> typesEvento = new ArrayList<TypeEvento>();
    	
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
			JsonArray typeEventoArray = json.getArray("EventsTypes");
			for(int i = 0; i < typeEventoArray.length(); i++) {				
				JsonObject type = typeEventoArray.getObject(i);
				
				TypeEvento typeEvento = new TypeEvento();

				typeEvento.setId((int)type.getNumber("Id"));
				typeEvento.setType(type.getString("Type"));
				typeEvento.setDescription(type.getString("Description"));
		        
				typesEvento.add(typeEvento);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
    	
		return typesEvento;
	}
	
	public static boolean createTypeCasa(TypeEvento typeEvento) {
		
		try {
			String params = URLEncoder.encode(typeEvento.getType(), "UTF-8") + "," +
					URLEncoder.encode(typeEvento.getDescription(), "UTF-8");
			String urlString = Constants.rootURL + CREATE_URL;
			URL url = new URL(urlString+params);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			con.setRequestMethod("POST");
			
			int a = con.getResponseCode();
			System.out.println(a);
		
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean deleteTypeCasa(TypeEvento typeEvento) {
		try {
    		URL url = new URL(Constants.rootURL + DELETE_URL +typeEvento.getId());
    		HttpURLConnection con = (HttpURLConnection) url.openConnection();

    		con.setRequestMethod("POST");
    		
    		int a = con.getResponseCode();
			System.out.println(a);
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean editTypeCasa(TypeEvento typeEvento) {
		
		try {
			String params = URLEncoder.encode(String.valueOf(typeEvento.getId()), "UTF-8") + "," +
					URLEncoder.encode(typeEvento.getType(), "UTF-8") + "," +
					URLEncoder.encode(typeEvento.getDescription(), "UTF-8");
			String urlString = Constants.rootURL + EDIT_URL;
			URL url = new URL(urlString+params);
					
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			con.setRequestMethod("POST");
			
			int a = con.getResponseCode();
			System.out.println(a);
		
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
