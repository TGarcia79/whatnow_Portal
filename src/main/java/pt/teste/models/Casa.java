package pt.teste.models;

public class Casa {
	
	private String name;
	private String commercial_name;
	private String nif;
	private String mail;
	private String phone;
	private String address;
	private String description;
	private Double latitude;
	private Double longitude;
	private TypeCasa type;
	
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
	public String getType() {
		return type.getType();
	}
	public void setType(TypeCasa type) {
		this.type = type;
	}
	
	
}
