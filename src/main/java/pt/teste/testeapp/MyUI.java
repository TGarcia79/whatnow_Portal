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

import pt.teste.views.DefaultView;
import pt.teste.views.ViewCasas;
import pt.teste.views.ViewEventos;
import pt.teste.views.ViewTypeCasa;
import pt.teste.views.ViewTypeEvento;
import pt.teste.views.ViewUsers;

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
        
        Button button_casas = new Button("Casas", e -> getNavigator().navigateTo("ViewCasas"));
        button_casas.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        
        Button button_users = new Button("Users", e -> getNavigator().navigateTo("ViewUsers"));
        button_users.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        
        Button button_tipoEvento = new Button("Tipo Evento", e -> getNavigator().navigateTo("ViewTypeEvento"));
        button_tipoEvento.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        
        Button button_tipoSpot = new Button("Tipo Spot", e -> getNavigator().navigateTo("ViewTypeCasa"));
        button_tipoSpot.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
           
        
        CssLayout menu = new CssLayout(title, button_eventos, button_casas, button_users, button_tipoEvento, button_tipoSpot);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        
        CssLayout viewContainer = new CssLayout();

        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", DefaultView.class);
        navigator.addView("ViewEventos", ViewEventos.class);
        navigator.addView("ViewCasas", ViewCasas.class);
        navigator.addView("ViewTypeCasa", ViewTypeCasa.class);
        navigator.addView("ViewUsers", ViewUsers.class);
        navigator.addView("ViewTypeEvento", ViewTypeEvento.class);
        //navigator.addView("view2", View2.class);
        
        setContent(mainLayout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
