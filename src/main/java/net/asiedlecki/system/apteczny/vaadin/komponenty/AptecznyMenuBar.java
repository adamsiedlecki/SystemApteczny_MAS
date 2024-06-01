package net.asiedlecki.system.apteczny.vaadin.komponenty;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AptecznyMenuBar {

    public static void dodajMenuBar(VerticalLayout target) {
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Strona główna", e -> navigateTo(""));
        menuBar.addItem("Realizacja recepty", e -> navigateTo("realizacja-recepty"));

        target.add(new Text("System Apteczny MAS"));
        target.add(menuBar);
    }

    private static void navigateTo(String target) {
        UI.getCurrent().navigate(target);
    }
}
