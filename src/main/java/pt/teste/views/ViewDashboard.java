package pt.teste.views;

import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import pt.teste.models.Evento;
import pt.teste.models.User;


public class ViewDashboard extends Composite implements View {

    public ViewDashboard() {
        
    	 final VerticalLayout layout = new VerticalLayout();
    	 
    	 BrowserFrame browser = new BrowserFrame("Browser",
         	    new ExternalResource("https://app.powerbi.com/view?r=eyJrIjoiMTU5NTNiMDQtYjA5Ni00NGUyLWFiYWMtMDU4NmMzOGEyZGQxIiwidCI6IjU4MDRhNWVkLWJkZTktNDQzZC1hY2M1LTU5NTVkOGM3ZjE2NyIsImMiOjh9"));
         	browser.setWidth("800px");
         	browser.setHeight("600px");
         	layout.addComponent(browser);
    	 
         setCompositionRoot(layout);
    	
    }
}