package pt.teste.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Evento;
import pt.teste.models.TypeCasa;


public class ViewTypeCasa extends Composite implements View {

    public ViewTypeCasa() {
        
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
         
         TypeCasa typeCasa = new TypeCasa();
         
         typeCasa.setType("bar");
         typeCasa.setDescription("bar com musica ao vivo");
         
         Grid<TypeCasa> grid = new Grid<>(TypeCasa.class);
    
         grid.setItems(typeCasa);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}