package pt.teste.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import pt.teste.testeapp.Constants;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;

public class Casa {
	
	private final static String LIST_URL = "/v1/spot/list";
	private final static String CREATE_URL = "/v1/spot/create?SPOT=";
	private final static String DELETE_URL = "/v1/spot/delete?spotId=";
	private final static String EDIT_URL = "/v1/spot/edit?spot=";
	
	private int id;
	private String name;
	private String commercial_name;
	private String nif;
	private String mail;
	private String phone;
	private String address;
	private String description;
	private Double latitude;
	private Double longitude;
	private User user;
	private TypeCasa type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommercial_name() {
		return commercial_name;
	}
	public void setCommercial_name(String commercial_name) {
		this.commercial_name = commercial_name;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type.getType();
	}
	public void setType(TypeCasa type) {
		this.type = type;
	}
	
	public TypeCasa getTypeObj() {
		return this.type;
	}
	
	public static ArrayList<Casa> getCasas(){
		
		ArrayList<Casa> listaCasas = new ArrayList<Casa>();
    	
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
			JsonArray spotArray = json.getArray("SPOTs");
			for(int i = 0; i < spotArray.length(); i++) {				
				JsonObject spot = spotArray.getObject(i);
				
				Casa casa = new Casa();
		        TypeCasa typeCasa = new TypeCasa();
		        User user = new User();
		        
		        casa.setId((int)spot.getNumber("Id"));
		        casa.setName(spot.getString("Name"));
		        casa.setCommercial_name(spot.getString("Commercial_Name"));
		        casa.setNif(spot.getString("NIF"));
		        casa.setMail(spot.getString("Mail"));
		        casa.setPhone(spot.getString("Phone"));
		        casa.setAddress(spot.getString("Address"));
		        casa.setDescription(spot.getString("Description"));
		        JsonObject coordinates = spot.get("Coordinates");
		        casa.setLatitude(coordinates.getNumber("y"));
		        casa.setLongitude(coordinates.getNumber("x"));
		        JsonObject type = spot.get("Type");
		        typeCasa.setId((int)type.getNumber("id"));
		        typeCasa.setType(type.getString("type"));
		        typeCasa.setDescription(type.getString("description"));
		        casa.setType(typeCasa);
		        JsonObject _user = spot.get("USER");
		        user.setId((int)_user.getNumber("id"));
		        user.setUsername(_user.getString("Username"));
		        user.setName(_user.getString("Name"));
		        user.setSurname(_user.getString("Surname"));
		        user.setMail(_user.getString("Mail"));
		        user.setPhone(_user.getString("Phone"));
		        casa.setUser(user);
		        
		        listaCasas.add(casa);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
    	
		return listaCasas;
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
