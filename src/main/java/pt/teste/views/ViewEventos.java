package pt.teste.views;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import elemental.json.Json;
import pt.teste.models.Evento;
import pt.teste.models.TypeEvento;


public class ViewEventos extends Composite implements View {

    public ViewEventos() {
        
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
         TypeEvento typeEvento = new TypeEvento();
         typeEvento.setType("Ladys Night");
         typeEvento.setDescription("Mulher nao paga jamais");
         
         evento.setName("Telmo");
         evento.setDateStart("23-03-2019");
         evento.setDateEnd("23-03-2019");
         evento.setDescription("asd");
         evento.setType(typeEvento);
         
         
         Grid<Evento> grid = new Grid<>(Evento.class);
         
         
         grid.setItems(evento);
         
         grid.setHeightUndefined();
         
         
         layout.addComponents(buttonNovo, buttonEditar, buttonEliminar, grid);
         
         setCompositionRoot(layout);
    	
    }
}