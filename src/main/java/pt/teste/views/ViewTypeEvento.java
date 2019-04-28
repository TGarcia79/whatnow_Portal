package pt.teste.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Evento;
import pt.teste.models.TypeEvento;


public class ViewTypeEvento extends Composite implements View {

    public ViewTypeEvento() {
        
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
         
         TypeEvento typeEvento = new TypeEvento();
         
         typeEvento.setType("Ladys Night");
         typeEvento.setDescription("Mulher nao paga");
         
         
         Grid<TypeEvento> grid = new Grid<>(TypeEvento.class);
         
         grid.setItems(typeEvento);
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}