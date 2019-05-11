package pt.teste.views;

import java.util.ArrayList;
import java.util.Set;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Casa;
import pt.teste.models.TypeCasa;


public class ViewCasas extends Composite implements View {

    public ViewCasas() {
    	
    	ArrayList<Casa> listaCasas = Casa.getCasas();
    	
		 final VerticalLayout layout = new VerticalLayout();
		 layout.setHeightUndefined();
		 
		 //Table
		 Grid<Casa> grid = new Grid<>(Casa.class);
		 //set/retrieve data to form
		 Binder<Casa> binder = new Binder<>(Casa.class);
		 
		 //Form fields
		 TextField nameField = new TextField();
		 binder.forField(nameField).asRequired("Insira o nome da casa").bind(Casa::getName, Casa::setName);
		 nameField.setVisible(false);
		 nameField.setCaption("Nome:");
		 
		 TextField commercialNameField = new TextField();
		 binder.forField(commercialNameField).asRequired("Insira o nome comercial da casa").bind(Casa::getCommercial_name, Casa::setCommercial_name);
		 commercialNameField.setVisible(false);
		 commercialNameField.setCaption("Nome comercial:");
		 
		 TextField nifField = new TextField();
		 binder.forField(nifField).asRequired("Insira o número fiscal da casa").bind(Casa::getNif, Casa::setNif);
		 nifField.setVisible(false);
		 nifField.setCaption("NIF:");
		 
		 TextField mailField = new TextField();
		 binder.forField(mailField).asRequired().withValidator(
				 new EmailValidator("This doesn't look like a valid email address"))
		 		.bind(Casa::getMail, Casa::setMail);
		 mailField.setVisible(false);
		 mailField.setCaption("E-Mail:");
		 
		 TextField phoneField = new TextField();
		 binder.forField(phoneField).asRequired("Insira um contacto telefónico").bind(Casa::getPhone, Casa::setPhone);
		 phoneField.setVisible(false);
		 phoneField.setCaption("Telefone:");
		 
		 TextField addressField = new TextField();
		 binder.forField(addressField).asRequired("Insira a morada da casa").bind(Casa::getAddress, Casa::setAddress);
		 addressField.setVisible(false);
		 addressField.setCaption("Morada:");
		 
		 TextField descriptionField = new TextField();
		 binder.forField(descriptionField).asRequired("Insira uma descrição da casa").bind(Casa::getDescription, Casa::setDescription);
		 descriptionField.setVisible(false);
		 descriptionField.setCaption("Descrição:");
		 
		 TextField latitudeField = new TextField();
		 binder.forField(latitudeField).asRequired().withConverter(new StringToDoubleConverter("tem de ser numero")).bind(Casa::getLatitude, Casa::setLatitude);
		 latitudeField.setVisible(false);
		 latitudeField.setCaption("Latitude:");
		 
		 TextField longitudeField = new TextField();
		 binder.forField(longitudeField).asRequired().withConverter(new StringToDoubleConverter("tem de ser numero")).bind(Casa::getLongitude, Casa::setLongitude);
		 longitudeField.setVisible(false);
		 longitudeField.setCaption("Longitude:");
		 
		 ComboBox<TypeCasa> comboBox = new ComboBox<>();
		 comboBox.setCaption("Tipo:");
		 comboBox.setItemCaptionGenerator(TypeCasa::getType);

		 comboBox.setItems(TypeCasa.getTypesCasa());
		 comboBox.setVisible(false);
	
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
	     });
	     
	     grid.removeColumn("id");
	     grid.setColumns("name", "commercial_name", "nif", "mail", "phone", "address", "latitude", "longitude", "description", "type");
	     grid.setItems(listaCasas);
	     
	     
	     
	     layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, buttonGuardar, 
	    		 buttonReset, buttonCancelar, grid, nameField, commercialNameField,
	    		 nifField, mailField, phoneField, addressField, descriptionField, latitudeField,
	    		 longitudeField, comboBox);
	     
	     setCompositionRoot(layout);
    	
    }
}