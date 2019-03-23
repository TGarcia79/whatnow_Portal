package pt.teste.testeapp;

public class Evento {
	
	private String name;
	private String date_start;
	private String date_end;
	private String description;
	private String type;
	
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
		return this.type;
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
	
	public void setType(String type){
		this.type = type;
	}

}
