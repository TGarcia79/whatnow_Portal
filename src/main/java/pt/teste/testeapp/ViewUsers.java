package pt.teste.testeapp;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class ViewUsers extends Composite implements View {

    public ViewUsers() {
        
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
         
         Evento evento = new Evento();
         
         evento.setName("Telmo");
         evento.setDateStart("23-03-2019");
         evento.setDateEnd("23-03-2019");
         evento.setDescription("asd");
         evento.setType("qwe");
         
         Grid<Evento> grid = new Grid<>(Evento.class);
         
         grid.addColumn(Evento::getDateStart)
 			.setCaption("Date Start");
         grid.addColumn(Evento::getDateEnd)
 			.setCaption("Date End");
         grid.addColumn(Evento::getDescription)
 			.setCaption("Description");
         grid.addColumn(Evento::getType)
 			.setCaption("Type");
     
         grid.setItems(evento);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}