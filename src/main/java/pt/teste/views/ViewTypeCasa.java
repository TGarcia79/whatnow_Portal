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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.TypeCasa;


public class ViewTypeCasa extends Composite implements View {

    public ViewTypeCasa() {
    	
    	ArrayList<TypeCasa> typesCasa = TypeCasa.getTypesCasa();
        
    	final VerticalLayout layout = new VerticalLayout();
    	final HorizontalLayout buttonLayout = new HorizontalLayout();
		 
		 //Table
		 Grid<TypeCasa> grid = new Grid<>(TypeCasa.class);
		 //set/retrieve data to form
		 Binder<TypeCasa> binder = new Binder<>(TypeCasa.class);
		 
		 //Form fields
		 TextField typeField = new TextField();
		 binder.forField(typeField).asRequired("Insira um tipo").bind(TypeCasa::getType, TypeCasa::setType);
		 typeField.setVisible(false);
		 typeField.setCaption("Tipo:");
		 
		 TextField descriptionField = new TextField();
		 binder.forField(descriptionField).asRequired("Insira uma descrição").bind(TypeCasa::getDescription, TypeCasa::setDescription);
		 descriptionField.setVisible(false);
		 descriptionField.setCaption("Descrição:");
	
		 //Buttons
	     Button buttonNovo = new Button("Novo");
	     Button buttonEditar = new Button("Editar");
	     Button buttonEliminar = new Button("Eliminar");
	     Button buttonGuardar = new Button("Guardar");
	     Button buttonReset = new Button("Reset");
	     Button buttonCancelar = new Button("Cancelar");
	     
	     buttonLayout.addComponents(buttonNovo, buttonEditar,buttonEliminar, buttonGuardar, buttonReset, buttonCancelar);
	     
	     buttonGuardar.setVisible(false);
		 buttonReset.setVisible(false);
		 buttonCancelar.setVisible(false);
	     
	     //Listeners
	     buttonNovo.addClickListener(e -> {
	    	 
	    	 TypeCasa typeCasa = new TypeCasa();
	    	 
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
					binder.writeBean(typeCasa);
					TypeCasa.createTypeCasa(typeCasa);
					Page.getCurrent().reload();
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	   	     });
	    	 
	    	 buttonReset.addClickListener(ev -> {
	    		 binder.readBean(typeCasa);
		     });
	     });
	     
		
	     buttonCancelar.addClickListener(e -> {
	    	 Page.getCurrent().reload();
	     });
	     
	     buttonEditar.addClickListener(e -> {
	    	 Set<TypeCasa> typeCasas = grid.getSelectedItems();
	         if (!typeCasas.isEmpty()) {
	        	 for(TypeCasa typeCasa : typeCasas){
	        		 binder.readBean(typeCasa);
	        		 
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
							binder.writeBean(typeCasa);
							TypeCasa.editTypeCasa(typeCasa);
		        	    	Page.getCurrent().reload();
						} catch (ValidationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	     });
	        		 
	        		 buttonReset.addClickListener(ev -> {
	        			 binder.readBean(typeCasa);
	        	     });
	        	 }
	         }
	     });
	     
	     buttonEliminar.addClickListener(e -> {
	    	 Set<TypeCasa> typeCasas = grid.getSelectedItems();
	         if (!typeCasas.isEmpty()) {
	        	 for(TypeCasa typeCasa : typeCasas){
	        		 TypeCasa.deleteTypeCasa(typeCasa);
	        	 }
	        	 Page.getCurrent().reload();
	         }
	     });
         
         grid.removeColumn("id");
	     grid.setColumns("type", "description");
         grid.setItems(typesCasa);
         grid.setCaption("Tipos de casa");
         
         
         layout.addComponents(buttonLayout, grid, typeField, descriptionField);
         
         setCompositionRoot(layout);
    	
    }
}