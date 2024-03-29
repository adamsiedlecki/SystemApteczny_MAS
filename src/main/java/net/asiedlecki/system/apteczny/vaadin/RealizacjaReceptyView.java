package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.vaadin.komponenty.AptecznyMenuBar;

@Route("realizacja-recepty")
public class RealizacjaReceptyView extends VerticalLayout {

    public RealizacjaReceptyView() {
        AptecznyMenuBar.dodajMenuBar(this);
    }

}