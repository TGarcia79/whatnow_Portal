package pt.teste.testeapp;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
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
        
        
        layout.addComponents(name, button, grid);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
