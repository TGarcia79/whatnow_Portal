package pt.teste.models;

public class Evento {
	
	private final static String LIST_URL = "/v1/spottype/list";
	private final static String CREATE_URL = "/v1/spottype/create?spotType=";
	private final static String DELETE_URL = "/v1/spottype/delete?spotTypeId=";
	private final static String EDIT_URL = "/v1/spottype/edit?spotType=";
	
	private String name;
	private String date_start;
	private String date_end;
	private String description;
	private TypeEvento type;
	
	public String getName(){
		return this.name;
	}
	
	public String getDateStart(){
		return this.date_start;
	}
	
	public String getDateEnd(){
		return this.date_end;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getType(){
		return this.type.getType();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDateStart(String date_start){
		this.date_start = date_start;
	}
	
	public void setDateEnd(String date_end){
		this.date_end = date_end;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setType(TypeEvento type){
		this.type = type;
	}

}
