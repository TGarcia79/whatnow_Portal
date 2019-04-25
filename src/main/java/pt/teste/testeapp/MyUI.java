package pt.teste.testeapp;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

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
        
    	 Label title = new Label("Menu");
    	 
         title.addStyleName(ValoTheme.MENU_TITLE);
    	
        Button button_eventos = new Button("Eventos", e -> getNavigator().navigateTo("ViewEventos"));
        button_eventos.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        
        Button button_casas = new Button("Casas");
        button_casas.addClickListener(e -> {
            
        });
        
        Button button_users = new Button("Users");
        button_users.addClickListener(e -> {
           
        });
        
        Button button_tipoEventos = new Button("Tipo Eventos");
        button_tipoEventos.addClickListener(e -> {
           
        });
        
        Button button_tipoSpot = new Button("Tipo Spot");
        button_tipoSpot.addClickListener(e -> {
           
        });
        
        CssLayout menu = new CssLayout(title, button_eventos);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        
        CssLayout viewContainer = new CssLayout();

        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", DefaultView.class);
        navigator.addView("ViewEventos", ViewEventos.class);
        //navigator.addView("view2", View2.class);
        
        setContent(mainLayout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
