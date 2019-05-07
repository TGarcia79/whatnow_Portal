package pt.teste.views;

import java.util.ArrayList;
import java.util.Set;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Evento;
import pt.teste.models.TypeCasa;
import pt.teste.models.TypeEvento;


public class ViewTypeEvento extends Composite implements View {

    public ViewTypeEvento() {
        
    	ArrayList<TypeEvento> typesEvento = TypeEvento.getTypesEvento();
        
    	final VerticalLayout layout = new VerticalLayout();
		 
		 //Table
		 Grid<TypeEvento> grid = new Grid<>(TypeEvento.class);
		 //set/retrieve data to form
		 Binder<TypeEvento> binder = new Binder<>(TypeEvento.class);
		 
		 //Form fields
		 TextField typeField = new TextField();
		 binder.forField(typeField).bind(TypeEvento::getType, TypeEvento::setType);
		 typeField.setVisible(false);
		 typeField.setCaption("Tipo:");
		 
		 TextField descriptionField = new TextField();
		 binder.forField(descriptionField).bind(TypeEvento::getDescription, TypeEvento::setDescription);
		 descriptionField.setVisible(false);
		 descriptionField.setCaption("Descrição:");
	
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
	     buttonNovo.addClickListener(e -> {
	    	 
	    	 TypeEvento typeEvento = new TypeEvento();
	    	 
	    	 typeField.setVisible(true);
	   		 descriptionField.setVisible(true);
	   		 
	   		 grid.setVisible(false);
	   		 buttonNovo.setVisible(false);
	   		 buttonEditar.setVisible(false);
	   		 buttonEliminar.setVisible(false);
	   		 buttonGuardar.setVisible(true);
	   		 buttonReset.setVisible(true);
	   		 buttonCancelar.setVisible(true);
   		 
	    	 buttonGuardar.addClickListener(ev -> {
	    		 
	    		 try {
					binder.writeBean(typeEvento);
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		 TypeEvento.createTypeCasa(typeEvento);
	   	    	 Page.getCurrent().reload();
	   	     });
	    	 
	    	 buttonReset.addClickListener(ev -> {
	    		 binder.readBean(typeEvento);
		     });
	     });
	     
		
	     buttonCancelar.addClickListener(e -> {
	    	 Page.getCurrent().reload();
	     });
	     
	     buttonEditar.addClickListener(e -> {
	    	 Set<TypeEvento> typeEventos = grid.getSelectedItems();
	         if (!typeEventos.isEmpty()) {
	        	 for(TypeEvento typeEvento : typeEventos){
	        		 binder.readBean(typeEvento);
	        		 
	        		 typeField.setVisible(true);
	        		 descriptionField.setVisible(true);
	        		 
	        		 grid.setVisible(false);
	        		 buttonNovo.setVisible(false);
	        		 buttonEditar.setVisible(false);
	        		 buttonEliminar.setVisible(false);
	        		 buttonGuardar.setVisible(true);
	        		 buttonReset.setVisible(true);
	        		 buttonCancelar.setVisible(true);
	        		 
	        		 buttonGuardar.addClickListener(ev -> {
	        			 try {
							binder.writeBean(typeEvento);
						} catch (ValidationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        			 TypeEvento.editTypeCasa(typeEvento);
	        	    	 Page.getCurrent().reload();
	        	     });
	        		 
	        		 buttonReset.addClickListener(ev -> {
	        			 binder.readBean(typeEvento);
	        	     });
	        	 }
	         }
	     });
	     
	     buttonEliminar.addClickListener(e -> {
	    	 Set<TypeEvento> typeEventos = grid.getSelectedItems();
	         if (!typeEventos.isEmpty()) {
	        	 for(TypeEvento typeEvento : typeEventos){
	        		 TypeEvento.deleteTypeCasa(typeEvento);
	        	 }
	        	 Page.getCurrent().reload();
	         }
	     });
         
         grid.removeColumn("id");
	     grid.setColumns("type", "description");
         grid.setItems(typesEvento);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, buttonGuardar, 
	    		 buttonReset, buttonCancelar, grid, typeField, descriptionField);
         
         setCompositionRoot(layout);
    	
    }
}