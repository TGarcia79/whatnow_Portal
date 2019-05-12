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
import com.vaadin.ui.HorizontalLayout;
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
		 final HorizontalLayout buttonLayout = new HorizontalLayout();
		 final HorizontalLayout textFieldLayout1 = new HorizontalLayout();
		 final HorizontalLayout textFieldLayout2 = new HorizontalLayout();
		 final HorizontalLayout textFieldLayout3 = new HorizontalLayout();
		 
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
		 
		 TextField descriptionField = new TextField();
		 binder.forField(descriptionField).bind(Evento::getDescription, Evento::setDescription);
		 descriptionField.setVisible(false);
		 descriptionField.setCaption("Descrição:");
		 
		 DateField dateStartField = new DateField();
		 binder.forField(dateStartField).bind(Evento::getDateStart, Evento::setStartLocalDate);
		 dateStartField.setVisible(false);
		 dateStartField.setCaption("Data inicio:");
		 
		 TextField timeStartField = new TextField();
		 binder.forField(timeStartField).bind(Evento::getTimeStart, Evento::setTimeStart);
		 timeStartField.setVisible(false);
		 timeStartField.setCaption("Hora inicio:");
		 timeStartField.setPlaceholder("21:00");
		 
		 DateField dateEndField = new DateField();
		 binder.forField(dateEndField).bind(Evento::getDateEnd, Evento::setEndLocalDate);
		 dateEndField.setVisible(false);
		 dateEndField.setCaption("Data fim:");
		 
		 TextField timeEndField = new TextField();
		 binder.forField(timeEndField).bind(Evento::getTimeEnd, Evento::setTimeEnd);
		 timeEndField.setVisible(false);
		 timeEndField.setCaption("Hora fim:");
		 timeEndField.setPlaceholder("23:00");
		 
		 ComboBox<TypeEvento> comboBox = new ComboBox<>();
		 comboBox.setCaption("Tipo:");
		 comboBox.setItemCaptionGenerator(TypeEvento::getType);

		 comboBox.setItems(TypeEvento.getTypesEvento());
		 comboBox.setVisible(false);
		 
		 textFieldLayout1.addComponents(nameField, descriptionField);
		 textFieldLayout2.addComponents(dateStartField, timeStartField);
		 textFieldLayout3.addComponents(dateEndField, timeEndField);
	
		 //Buttons
	     Button buttonNovo = new Button("Novo");
	     Button buttonEditar = new Button("Editar");
	     Button buttonEliminar = new Button("Eliminar");
	     Button buttonGuardar = new Button("Guardar");
	     Button buttonReset = new Button("Reset");
	     Button buttonCancelar = new Button("Cancelar");
	     
	     buttonLayout.addComponents(buttonNovo, buttonEditar, buttonEliminar, buttonGuardar, buttonReset, buttonCancelar);
	     
	     buttonGuardar.setVisible(false);
		 buttonReset.setVisible(false);
		 buttonCancelar.setVisible(false);
	     
	     //Listeners
	     buttonNovo.addClickListener(e -> {
	    	 
	    	 Evento evento = new Evento();
	    	 
	    	 nameField.setVisible(true);
	    	 descriptionField.setVisible(true);
	    	 dateStartField.setVisible(true);
	   		 dateEndField.setVisible(true);
	   		 timeStartField.setVisible(true);
	   		 timeEndField.setVisible(true);
	   		 comboBox.setVisible(true);
	   		 
	   		 grid.setVisible(false);
	   		 buttonNovo.setVisible(false);
	   		 buttonEditar.setVisible(false);
	   		 buttonEliminar.setVisible(false);
	   		 buttonGuardar.setVisible(true);
	   		 buttonReset.setVisible(true);
	   		 buttonCancelar.setVisible(true);
   		 
	    	 buttonGuardar.addClickListener(ev -> {
	    		 evento.setType(comboBox.getValue());
	    		 //time postos manualmente porque binder não consegue! não percebi o porqu~e, está a funcionar
	    		 evento.setTimeStart(timeStartField.getValue());
	    		 evento.setTimeEnd(timeEndField.getValue());
	    		 try {
					binder.writeBean(evento);
					Evento.createEvento(evento);
					Page.getCurrent().reload();
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    	 
   	     });
	    	 
	    	 buttonReset.addClickListener(ev -> {
   			 binder.readBean(evento);
		     });
	     });
	     
		
	     buttonCancelar.addClickListener(e -> {
	    	 Page.getCurrent().reload();
	     });
	     
	     buttonEditar.addClickListener(e -> {
	    	 Set<Evento> eventos = grid.getSelectedItems();
	         if (!eventos.isEmpty()) {
	        	 for(Evento evento : eventos){
	        		 binder.readBean(evento);
	        		 
	        		 nameField.setVisible(true);
	    	    	 descriptionField.setVisible(true);
	    	    	 dateStartField.setVisible(true);
	    	   		 dateEndField.setVisible(true);
	    	   		 timeStartField.setVisible(true);
	    	   		 timeEndField.setVisible(true);
	    	   		 comboBox.setVisible(true);
	        		 
	        		 grid.setVisible(false);
	        		 gridAttr.setVisible(false);
	        		 buttonNovo.setVisible(false);
	        		 buttonEditar.setVisible(false);
	        		 buttonEliminar.setVisible(false);
	        		 buttonGuardar.setVisible(true);
	        		 buttonReset.setVisible(true);
	        		 buttonCancelar.setVisible(true);
	        		 
	        		 comboBox.setSelectedItem(evento.getTypeObj());
	        		 
	        		 buttonGuardar.addClickListener(ev -> {
	        			 evento.setType(comboBox.getValue());
	        			 try {
							binder.writeBean(evento);
							Evento.editEvento(evento);
		        	    	Page.getCurrent().reload();
						} catch (ValidationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	    	 
	        	     });
	        		 
	        		 buttonReset.addClickListener(ev -> {
	        			 binder.readBean(evento);
	        	     });
	        	 }
	         }
	     });
	     
	     buttonEliminar.addClickListener(e -> {
	         Set<Evento> eventos = grid.getSelectedItems();
	         if (!eventos.isEmpty()) {
	        	 for(Evento evento : eventos){
	        		 Evento.deleteEvento(evento);
	        	 }
	        	 Page.getCurrent().reload();
	         }
	     });
	     
	     grid.removeColumn("id");
	     grid.setColumns("name", "dateStart", "dateEnd", "description", "type");
	     grid.setItems(listaEventos);
	     grid.setHeight("300");
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
	     
	     
	     layout.addComponents(buttonLayout, grid, gridAttr, textFieldLayout1, textFieldLayout2, textFieldLayout3, comboBox);
	     
	     setCompositionRoot(layout);
    	
    }
}