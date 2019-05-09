package pt.teste.views;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Composite;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import elemental.json.Json;
import pt.teste.models.Atribute;
import pt.teste.models.Casa;
import pt.teste.models.Evento;
import pt.teste.models.TypeCasa;
import pt.teste.models.TypeEvento;


public class ViewEventos extends Composite implements View {

    public ViewEventos() {
        
    	ArrayList<Evento> listaEventos = Evento.getEventos();
    	
		 final VerticalLayout layout = new VerticalLayout();
		 
		 //Table
		 Grid<Evento> grid = new Grid<>(Evento.class);
		 Grid<Atribute> gridAttr = new Grid<Atribute>(Atribute.class);
		 //set/retrieve data to form
		 Binder<Evento> binder = new Binder<>(Evento.class);
		 
		 //Form fields
		 TextField nameField = new TextField();
		 binder.forField(nameField).bind(Evento::getName, Evento::setName);
		 nameField.setVisible(false);
		 nameField.setCaption("Nome:");
		 
		 /*DateField dateStartField = new DateField();
		 binder.forField(dateStartField).bind(Evento::getDateStart, Evento::setDateEnd);
		 dateStartField.setVisible(false);
		 dateStartField.setCaption("Data inicio:");
		 
		 TextField nifField = new TextField();
		 binder.forField(nifField).bind(Casa::getNif, Casa::setNif);
		 nifField.setVisible(false);
		 nifField.setCaption("NIF:");
		 
		 TextField mailField = new TextField();
		 binder.forField(mailField).bind(Casa::getMail, Casa::setMail);
		 mailField.setVisible(false);
		 mailField.setCaption("E-Mail:");
		 
		 TextField phoneField = new TextField();
		 binder.forField(phoneField).bind(Casa::getPhone, Casa::setPhone);
		 phoneField.setVisible(false);
		 phoneField.setCaption("Telefone:");
		 
		 TextField addressField = new TextField();
		 binder.forField(addressField).bind(Casa::getAddress, Casa::setAddress);
		 addressField.setVisible(false);
		 addressField.setCaption("Morada:");
		 
		 TextField descriptionField = new TextField();
		 binder.forField(descriptionField).bind(Casa::getDescription, Casa::setDescription);
		 descriptionField.setVisible(false);
		 descriptionField.setCaption("Descrição:");
		 
		 TextField latitudeField = new TextField();
		 binder.forField(latitudeField).withConverter(new StringToDoubleConverter("tem de ser numero")).bind(Casa::getLatitude, Casa::setLatitude);
		 latitudeField.setVisible(false);
		 latitudeField.setCaption("Latitude:");
		 
		 TextField longitudeField = new TextField();
		 binder.forField(longitudeField).withConverter(new StringToDoubleConverter("tem de ser numero")).bind(Casa::getLongitude, Casa::setLongitude);
		 longitudeField.setVisible(false);
		 longitudeField.setCaption("Longitude:");
		 
		 ComboBox<TypeCasa> comboBox = new ComboBox<>();
		 comboBox.setCaption("Tipo:");
		 comboBox.setItemCaptionGenerator(TypeCasa::getType);

		 comboBox.setItems(TypeCasa.getTypesCasa());
		 comboBox.setVisible(false);*/
	
		 //Buttons
	     Button buttonNovo = new Button("Novo");
	     Button buttonEditar = new Button("Editar");
	     Button buttonEliminar = new Button("Eliminar");
	     Button buttonGuardar = new Button("Guardar");
	     Button buttonReset = new Button("Reset");
	     Button buttonCancelar = new Button("Cancelar");
	     
	     buttonGuardar.setVisible(false);
		 buttonReset.setVisible(false);
		 buttonCancelar.setVisible(false);
	     
	     //Listeners
	     /*buttonNovo.addClickListener(e -> {
	    	 
	    	 Casa casa = new Casa();
	    	 
	    	 nameField.setVisible(true);
	   		 commercialNameField.setVisible(true);
	   		 nifField.setVisible(true);
	   		 mailField.setVisible(true);
	   		 phoneField.setVisible(true);
	   		 addressField.setVisible(true);
	   		 descriptionField.setVisible(true);
	   		 latitudeField.setVisible(true);
	   		 longitudeField.setVisible(true);
	   		 comboBox.setVisible(true);
	   		 
	   		 grid.setVisible(false);
	   		 buttonNovo.setVisible(false);
	   		 buttonEditar.setVisible(false);
	   		 buttonEliminar.setVisible(false);
	   		 buttonGuardar.setVisible(true);
	   		 buttonReset.setVisible(true);
	   		 buttonCancelar.setVisible(true);
   		 
	    	 buttonGuardar.addClickListener(ev -> {
	    		 casa.setType(comboBox.getValue());
	    		 try {
					binder.writeBean(casa);
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    	 Casa.createCasa(casa);
   	    	 Page.getCurrent().reload();
   	     });
	    	 
	    	 buttonReset.addClickListener(ev -> {
   			 binder.readBean(casa);
		     });
	     });
	     
		
	     buttonCancelar.addClickListener(e -> {
	    	 Page.getCurrent().reload();
	     });
	     
	     buttonEditar.addClickListener(e -> {
	    	 Set<Casa> casas = grid.getSelectedItems();
	         if (!casas.isEmpty()) {
	        	 for(Casa casa : casas){
	        		 binder.readBean(casa);
	        		 
	        		 nameField.setVisible(true);
	        		 commercialNameField.setVisible(true);
	        		 nifField.setVisible(true);
	        		 mailField.setVisible(true);
	        		 phoneField.setVisible(true);
	        		 addressField.setVisible(true);
	        		 descriptionField.setVisible(true);
	        		 latitudeField.setVisible(true);
	        		 longitudeField.setVisible(true);
	        		 comboBox.setVisible(true);
	        		 
	        		 grid.setVisible(false);
	        		 buttonNovo.setVisible(false);
	        		 buttonEditar.setVisible(false);
	        		 buttonEliminar.setVisible(false);
	        		 buttonGuardar.setVisible(true);
	        		 buttonReset.setVisible(true);
	        		 buttonCancelar.setVisible(true);
	        		 
	        		 comboBox.setSelectedItem(casa.getTypeObj());
	        		 
	        		 buttonGuardar.addClickListener(ev -> {
	        			 casa.setType(comboBox.getValue());
	        			 try {
							binder.writeBean(casa);
						} catch (ValidationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	    	 Casa.editCasa(casa);
	        	    	 Page.getCurrent().reload();
	        	     });
	        		 
	        		 buttonReset.addClickListener(ev -> {
	        			 binder.readBean(casa);
	        	     });
	        	 }
	         }
	     });
	     
	     buttonEliminar.addClickListener(e -> {
	         Set<Casa> casas = grid.getSelectedItems();
	         if (!casas.isEmpty()) {
	        	 for(Casa casa : casas){
	        		 Casa.deleteCasa(casa);
	        	 }
	        	 Page.getCurrent().reload();
	         }
	     });*/
	     
	     grid.removeColumn("id");
	     grid.setColumns("name", "dateStart", "dateEnd", "description", "type");
	     grid.setItems(listaEventos);
	     gridAttr.removeColumn("id");
	     gridAttr.setColumns("type","description");
	     gridAttr.setHeight("300");
	     gridAttr.setVisible(false);
	     
	     grid.addSelectionListener(event -> {
	    	 Set<Evento> eventoTemp = grid.getSelectedItems();
	    	 if (!eventoTemp.isEmpty()) {
	    		 for(Evento evento : eventoTemp) {
	    			 gridAttr.setItems(evento.getAtributes());
    				 gridAttr.setVisible(true);
		    	 }
	    	 }
	     });
	     
	     
	     layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, buttonGuardar, 
	    		 buttonReset, buttonCancelar, grid, gridAttr/*, nameField, commercialNameField,
	    		 nifField, mailField, phoneField, addressField, descriptionField, latitudeField,
	    		 longitudeField, comboBox*/);
	     
	     setCompositionRoot(layout);
    	
    }
}