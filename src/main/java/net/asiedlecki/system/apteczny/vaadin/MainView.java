package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.vaadin.komponenty.AptecznyMenuBar;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        AptecznyMenuBar.dodajMenuBar(this);
        add(new Text("Witaj w systemie aptecznym!"));
    }

}