package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.AptekaDao;
import net.asiedlecki.system.apteczny.model.dokumenty.Recepta;
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
                Notification.show("System krajowy potwierdza istnienie recepty na lek: " + recepta.getProduktLeczniczy().utworzOpis());
            } else {
                Notification.show("Nie znaleziono recepty w systemie krajowym");
            }
        }));
        add(new ComboBox<>("Lek do wydania", AptekaDao.pobierzProduktyLeczniczeWBazieDanychApteki()));
        add(new Button("Zrealizuj recepte", event -> {
            Notification.show("Nie zimplementowano TODO");
        }));
    }

}