package pt.teste.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Evento;
import pt.teste.models.User;


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
         
         User user = new User();
         
         user.setUsername("tgarcia");
         user.setName("Telmo");
         user.setSurname("Garcia");
         user.setMail("mail@mail.mail");
         user.setPhone("962907756");
         
         Grid<User> grid = new Grid<>(User.class);
         
         /*
         grid.addColumn(Evento::getDateStart)
 			.setCaption("Date Start");
         grid.addColumn(Evento::getDateEnd)
 			.setCaption("Date End");
         grid.addColumn(Evento::getDescription)
 			.setCaption("Description");
         grid.addColumn(Evento::getType)
 			.setCaption("Type");
     	*/
     
         grid.setItems(user);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}