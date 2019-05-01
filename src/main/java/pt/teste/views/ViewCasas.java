package pt.teste.views;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.vaadin.navigator.View;
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
        
    	/*try {
			URL url = new URL("http://85.245.44.51:8080/v1/spot?SpotId=1");
	    	HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			String text = new Scanner(con.getInputStream()).useDelimiter("\\A").next();
			System.out.println(text);
			JsonObject json = Json.parse(text);
			JsonArray spot = json.getArray("SPOT");
			for(int i = 0; i < spot.length(); i++) {
				System.out.println(spot.getNumber(0));
			}*/
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	 final VerticalLayout layout = new VerticalLayout();

         Button buttonNovo = new Button("Novo");
         buttonNovo.addClickListener(e -> {
             
         });
         
         Button buttonEditar = new Button("Editar");
         buttonEditar.addClickListener(e -> {
             
         });

         Button buttonEliminar = new Button("Eliminar");
         buttonEliminar.addClickListener(e -> {
             
         });
         
         Casa casa = new Casa();
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
         casa.setType(typeCasa);
         
         Grid<Casa> grid = new Grid<>(Casa.class);
         
         /*grid.addColumn(Casa::getName)
 			.setCaption("Nome");
         grid.addColumn(Casa::getCommercial_name)
 			.setCaption("Nome Comercial");
         grid.addColumn(Casa::getDescription)
 			.setCaption("Description");
         grid.addColumn(Casa::getType)
 			.setCaption("Type");*/
     
         grid.setItems(casa);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}