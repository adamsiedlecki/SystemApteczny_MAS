package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.dokumenty.Recepta;
import net.asiedlecki.system.apteczny.serwisy.PobieranieReceptZKrajowegoSystemuService;
import net.asiedlecki.system.apteczny.vaadin.komponenty.AptecznyMenuBar;
import org.springframework.stereotype.Component;

@Component
@Route("realizacja-recepty")
public class RealizacjaReceptyView extends VerticalLayout {

    public RealizacjaReceptyView(PobieranieReceptZKrajowegoSystemuService pobieranieReceptZKrajowegoSystemuService) {
        AptecznyMenuBar.dodajMenuBar(this);

        add(new TextField("id dokumentu recepty od pacjenta", event -> {
            Recepta recepta = pobieranieReceptZKrajowegoSystemuService.pobierz(event.getValue());
            if (recepta != null) {
                Notification.show("System krajowy potwierdza istnienie recepty");
            } else {
                Notification.show("Nie znaleziono recepty w systemie krajowym");
            }
        }));
    }

}