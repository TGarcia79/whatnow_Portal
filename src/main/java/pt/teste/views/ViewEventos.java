package pt.teste.views;

import java.util.ArrayList;
import java.util.Set;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Composite;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Atribute;
import pt.teste.models.Casa;
import pt.teste.models.Evento;
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
		 binder.forField(nameField).asRequired("Insira um nome").bind(Evento::getName, Evento::setName);
		 nameField.setVisible(false);
		 nameField.setCaption("Nome:");
		 
		 TextField descriptionField = new TextField();
		 binder.forField(descriptionField).asRequired("Insira uma descrição").bind(Evento::getDescription, Evento::setDescription);
		 descriptionField.setVisible(false);
		 descriptionField.setCaption("Descrição:");
		 
		 DateField dateStartField = new DateField();
		 binder.forField(dateStartField).asRequired("Insira uma data").bind(Evento::getDateStart, Evento::setStartLocalDate);
		 dateStartField.setVisible(false);
		 dateStartField.setCaption("Data inicio:");
		 
		 TextField timeStartField = new TextField();
		 binder.forField(timeStartField).asRequired("Insira uma hora").bind(Evento::getTimeStart, Evento::setTimeStart);
		 timeStartField.setVisible(false);
		 timeStartField.setCaption("Hora inicio:");
		 timeStartField.setPlaceholder("21:00");
		 
		 DateField dateEndField = new DateField();
		 binder.forField(dateEndField).asRequired("Insira uma data").bind(Evento::getDateEnd, Evento::setEndLocalDate);
		 dateEndField.setVisible(false);
		 dateEndField.setCaption("Data fim:");
		 
		 TextField timeEndField = new TextField();
		 binder.forField(timeEndField).asRequired("Insira uma hora").bind(Evento::getTimeEnd, Evento::setTimeEnd);
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
	     Button buttonAttr = new Button("Novo Atributo");
	     
	     buttonLayout.addComponents(buttonNovo, buttonEditar, buttonEliminar, buttonGuardar, buttonReset, buttonCancelar);
	     
	     buttonGuardar.setVisible(false);
		 buttonReset.setVisible(false);
		 buttonCancelar.setVisible(false);
		 buttonAttr.setVisible(false);
	     
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
	   		 buttonAttr.setVisible(true);
	   		 
	   		 ArrayList<Binder<Atribute>> attrBinderL = new ArrayList<Binder<Atribute>>();
	   		 ArrayList<Atribute> atributes = new ArrayList<Atribute>();
	   		 
	   		 buttonAttr.addClickListener(ev -> {
	   			final HorizontalLayout attrLayout = new HorizontalLayout();
	   			Binder<Atribute> attrBinder = new Binder<>(Atribute.class);
	   			attrBinderL.add(attrBinder);
	   			 
	   			TextField attrType = new TextField();
	   			attrBinder.forField(attrType).asRequired("Insira um tipo").bind(Atribute::getType, Atribute::setType);
	   			attrType.setCaption("Tipo:");
	   			
	   			TextField attrDesc = new TextField();
	   			attrBinder.forField(attrDesc).asRequired("Insira uma descrição").bind(Atribute::getDescription, Atribute::setDescription);
	   			attrDesc.setCaption("Descrição:");
	   			
	   			Button buttonAttrEliminar = new Button("Eliminar");
	   			buttonAttrEliminar.setHeight("62px");
	   			
	   			buttonAttrEliminar.addClickListener(ev2 -> {
	   				layout.removeComponent(attrLayout);
	   				attrBinderL.remove(attrBinder);
	   			});
	   			
	   			attrLayout.addComponents(attrType, attrDesc, buttonAttrEliminar);
	   			layout.addComponent(attrLayout);
		     });
	   		 
	   		 
   		 
	    	 buttonGuardar.addClickListener(ev -> {	 
	    		 evento.setType(comboBox.getValue());
	    		 //binder não consegue "setar" as horas! não percebi o porque, está a funcionar "setando" manualmente
	    		 evento.setTimeStart(timeStartField.getValue());
	    		 evento.setTimeEnd(timeEndField.getValue());
	    		 try {
	    			 for(int i = 0; i < attrBinderL.size(); i++) {
	    				 Atribute atribute = new Atribute();
	    				 attrBinderL.get(i).writeBean(atribute);
	    				 atributes.add(atribute);
	    			 }
					binder.writeBean(evento);
					Evento.createEvento(evento, atributes);
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
	        		 buttonAttr.setVisible(true);
	    	   		 
	    	   		 ArrayList<Binder<Atribute>> attrBinderL = new ArrayList<Binder<Atribute>>();
	    	   		 ArrayList<Atribute> atributes = evento.getAtributes();
	    	   		 
	    	   		 if(!atributes.isEmpty()) {
	    	   			for(Atribute atribute : atributes) {
		    	   			final HorizontalLayout attrLayout = new HorizontalLayout();
		    	   			Binder<Atribute> attrBinder = new Binder<>(Atribute.class);
		    	   			attrBinderL.add(attrBinder);
		    	   			 
		    	   			TextField attrType = new TextField();
		    	   			attrBinder.forField(attrType).asRequired("Insira um tipo").bind(Atribute::getType, Atribute::setType);
		    	   			attrType.setCaption("Tipo:");
		    	   			
		    	   			TextField attrDesc = new TextField();
		    	   			attrBinder.forField(attrDesc).asRequired("Insira uma descrição").bind(Atribute::getDescription, Atribute::setDescription);
		    	   			attrDesc.setCaption("Descrição:");
		    	   			
		    	   			TextField attrState = new TextField();
		    	   			attrBinder.forField(attrState).withConverter(new StringToIntegerConverter("")).bind(Atribute::getState, Atribute::setState);
		    	   			attrState.setVisible(false);
		    	   			
		    	   			TextField attrId = new TextField();
		    	   			attrBinder.forField(attrId).withConverter(new StringToIntegerConverter("")).bind(Atribute::getId, Atribute::setId);
		    	   			attrId.setVisible(false);
		    	   			
		    	   			Button buttonAttrEliminar = new Button("Eliminar");
		    	   			buttonAttrEliminar.setHeight("62px");
		    	   			
		    	   			buttonAttrEliminar.addClickListener(ev2 -> {
		    	   				layout.removeComponent(attrLayout);
		    	   				atribute.setState(2);
		    	   			});
		    	   			
		    	   			attrBinder.readBean(atribute);
		    	   			
		    	   			attrLayout.addComponents(attrType, attrDesc, buttonAttrEliminar, attrState);
		    	   			layout.addComponent(attrLayout);
		    	   		 }
	    	   		 }	    	   		 
	    	   		 
	    	   		 buttonAttr.addClickListener(ev -> {
	    	   			final HorizontalLayout attrLayout = new HorizontalLayout();
	    	   			Binder<Atribute> attrBinder = new Binder<>(Atribute.class);
	    	   			attrBinderL.add(attrBinder);
	    	   			 
	    	   			TextField attrType = new TextField();
	    	   			attrBinder.forField(attrType).asRequired("Insira um tipo").bind(Atribute::getType, Atribute::setType);
	    	   			attrType.setCaption("Tipo:");
	    	   			
	    	   			TextField attrDesc = new TextField();
	    	   			attrBinder.forField(attrDesc).asRequired("Insira uma descrição").bind(Atribute::getDescription, Atribute::setDescription);
	    	   			attrDesc.setCaption("Descrição:");
	    	   			
	    	   			TextField attrState = new TextField();
	    	   			attrState.setValue("1");
	    	   			attrBinder.forField(attrState).withConverter(new StringToIntegerConverter("")).bind(Atribute::getState, Atribute::setState);
	    	   			attrState.setVisible(false);
	    	   			
	    	   			Button buttonAttrEliminar = new Button("Eliminar");
	    	   			buttonAttrEliminar.setHeight("62px");
	    	   			
	    	   			buttonAttrEliminar.addClickListener(ev2 -> {
	    	   				layout.removeComponent(attrLayout);
	    	   				attrBinderL.remove(attrBinder);
	    	   			});
	    	   			
	    	   			attrLayout.addComponents(attrType, attrDesc, buttonAttrEliminar, attrState);
	    	   			layout.addComponent(attrLayout);
	    		     });
	    	   		 
	        		 comboBox.setSelectedItem(evento.getTypeObj());
	        		 
	        		 buttonGuardar.addClickListener(ev -> {
	        			 evento.setType(comboBox.getValue());
	        			 try {
	        				 for(int i = 0; i < attrBinderL.size(); i++) {
	    	    				 Atribute atribute = new Atribute();
	    	    				 attrBinderL.get(i).writeBean(atribute);
	    	    				 if (atribute.getState() == 1) {
	    	    					 atributes.add(atribute);
	    	    				 } else if (atribute.getState() == 0) {
	    	    					 for(Atribute attr : atributes) {
	    	    						 if (attr.getId() == atribute.getId()){
	    	    							 attr.setType(atribute.getType());
	    	    							 attr.setDescription(atribute.getDescription());
	    	    						 }
	    	    					 }
	    	    				 }
	    	    			 }
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
	     grid.setColumns("name", "dateStart", "timeStart", "dateEnd", "timeEnd", "description", "type");
	     grid.setItems(listaEventos);
	     grid.setHeight("300");
	     grid.setCaption("Eventos");
	     gridAttr.removeColumn("id");
	     gridAttr.setColumns("type","description");
	     gridAttr.setHeight("300");
	     gridAttr.setVisible(false);
	     gridAttr.setCaption("Atributos");
	     
	     grid.addSelectionListener(event -> {
	    	 Set<Evento> eventoTemp = grid.getSelectedItems();
	    	 if (!eventoTemp.isEmpty()) {
	    		 for(Evento evento : eventoTemp) {
	    			 gridAttr.setItems(evento.getAtributes());
    				 gridAttr.setVisible(true);
		    	 }
	    	 }
	     });
	     
	     layout.addComponents(buttonLayout, grid, gridAttr, textFieldLayout1, textFieldLayout2, textFieldLayout3, comboBox, buttonAttr);
	     
	     setCompositionRoot(layout);
    	
    }
}