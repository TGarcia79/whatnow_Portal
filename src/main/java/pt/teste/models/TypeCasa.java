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

public class TypeCasa {
	
	private final static String LIST_URL = "/v1/spottype/list";
	private final static String CREATE_URL = "/v1/spottype/create?spotType=";
	private final static String DELETE_URL = "/v1/spottype/delete?spotTypeId=";
	private final static String EDIT_URL = "/v1/spottype/edit?spotType=";
	
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
	
public static ArrayList<TypeCasa> getTypesCasa(){
		
		ArrayList<TypeCasa> typesCasa = new ArrayList<TypeCasa>();
    	
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
			JsonArray typeCasaArray = json.getArray("SPOTsTypes");
			for(int i = 0; i < typeCasaArray.length(); i++) {				
				JsonObject type = typeCasaArray.getObject(i);
				
				TypeCasa typeCasa = new TypeCasa();

		        typeCasa.setId((int)type.getNumber("Id"));
		        typeCasa.setType(type.getString("Type"));
		        typeCasa.setDescription(type.getString("Description"));
		        
		        typesCasa.add(typeCasa);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
    	
		return typesCasa;
	}
	
	public static boolean createTypeCasa(TypeCasa typeCasa) {
		
		try {
			String params = URLEncoder.encode(typeCasa.getType(), "UTF-8") + "," +
					URLEncoder.encode(typeCasa.getDescription(), "UTF-8");
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
	
	public static boolean deleteTypeCasa(TypeCasa typeCasa) {
		try {
    		URL url = new URL(Constants.rootURL + DELETE_URL +typeCasa.getId());
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
	
	public static boolean editTypeCasa(TypeCasa typeCasa) {
		
		try {
			String params = URLEncoder.encode(String.valueOf(typeCasa.getId()), "UTF-8") + "," +
					URLEncoder.encode(typeCasa.getType(), "UTF-8") + "," +
					URLEncoder.encode(typeCasa.getDescription(), "UTF-8");
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
