package pt.teste.views;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import pt.teste.models.Casa;
import pt.teste.models.Evento;
import pt.teste.models.TypeCasa;


public class ViewCasas extends Composite implements View {

    public ViewCasas() {
    	
    	ArrayList<Casa> listaCasas = new ArrayList<Casa>();
    	
    	/*Casa casa = new Casa();
        TypeCasa typeCasa = new TypeCasa();
        
        typeCasa.setType("bar");
        typeCasa.setDescription("bar com musica ao vivo");
        
        casa.setName("Telmo");
        casa.setCommercial_name("uma coisa qq lda");
        casa.setNif("123456789");
        casa.setMail("mail@mail.mail");
        casa.setPhone("911231231");
        casa.setAddress("Rua das Canas, n18, 7dto, 1234-567 Alguidares de Baixo");
        casa.setDescription("bela casa");
        casa.setLatitude(38.708074);
        casa.setLongitude(-9.153111);
        casa.setType(typeCasa);*/
         
    	//Obter e mapear JSON
    	
    	try {
    		URL url = new URL("http://85.245.44.51:8080/v1/spot/list");
    		//URL url = new URL("http://localhost:8090/v1/spot/list");
	    	HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			String text = new Scanner(con.getInputStream()).useDelimiter("\\A").next();
			//System.out.println(text);
			JsonObject json = Json.parse(text);
			//System.out.println(json.getNumber("Id"));
			JsonArray spotArray = json.getArray("SPOTs");
			for(int i = 0; i < spotArray.length(); i++) {
				//System.out.println(spot.getObject(i).getNumber("Id"));
				
				JsonObject spot = spotArray.getObject(i);
				
				Casa casa = new Casa();
		        TypeCasa typeCasa = new TypeCasa();
		        
		        casa.setId((int)spot.getNumber("Id"));
		        casa.setName(spot.getString("Name"));
		        casa.setCommercial_name(spot.getString("Commercial_Name"));
		        casa.setNif(spot.getString("NIF"));
		        casa.setMail(spot.getString("Mail"));
		        casa.setPhone(spot.getString("Phone"));
		        casa.setAddress(spot.getString("Address"));
		        casa.setDescription(spot.getString("Description"));
		        JsonObject coordinates = spot.get(("Coordinates"));
		        casa.setLatitude(coordinates.getNumber("y"));
		        casa.setLongitude(coordinates.getNumber("x"));
		        typeCasa.setType(spot.getString("Type"));
		        casa.setType(typeCasa);
		        
		        listaCasas.add(casa);
			}
			//con.disconnect();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	 final VerticalLayout layout = new VerticalLayout();
    	 
    	 Grid<Casa> grid = new Grid<>(Casa.class);

         Button buttonNovo = new Button("Novo");
         buttonNovo.addClickListener(e -> {
             
         });
         
         Button buttonEditar = new Button("Editar");
         buttonEditar.addClickListener(e -> {
             
         });

         Button buttonEliminar = new Button("Eliminar");
         buttonEliminar.addClickListener(e -> {
             Set<Casa> casas = grid.getSelectedItems();
             if (!casas.isEmpty()) {
            	 //Casa[] casa = (Casa[]) casas.toArray();
            	 for(Casa casa : casas){
            		 try {
            			 	String urlParameters = "spotId="+casa.getId();
            			 	//byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            			 	//int    postDataLength = postData.length;
            	    		URL url = new URL("http://85.245.44.51:8080/v1/spot/delete");
            	    		HttpURLConnection con = (HttpURLConnection) url.openConnection();

            	    		//add reuqest header
            	    		con.setRequestMethod("POST");
            	    		//con.setRequestProperty("User-Agent", USER_AGENT);
            	    		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            	    		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
            	    		
            	    		// Send post request
            	    		con.setDoOutput(true);
            	    		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            	    		wr.writeBytes(urlParameters);
            	    		wr.flush();
            	    		wr.close();

            	    		int responseCode = con.getResponseCode();
            	    		System.out.println("\nSending 'POST' request to URL : " + url);
            	    		System.out.println("Post parameters : " + urlParameters);
            	    		System.out.println("Response Code : " + responseCode);
            				//con.disconnect();
            				Page.getCurrent().reload();
            				
            			} catch (IOException e1) {
            				e1.printStackTrace();
            			}
            	 }
             }
         });
         
         /*grid.addColumn(Casa::getName)
 			.setCaption("Nome");
         grid.addColumn(Casa::getCommercial_name)
 			.setCaption("Nome Comercial");
         grid.addColumn(Casa::getDescription)
 			.setCaption("Description");
         grid.addColumn(Casa::getType)
 			.setCaption("Type");*/
     
         grid.setItems(listaCasas);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}