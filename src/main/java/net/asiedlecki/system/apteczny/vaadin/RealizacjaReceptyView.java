package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.model.Lek;
import net.asiedlecki.system.apteczny.serwisy.PobieranieLekuZBazyService;
import net.asiedlecki.system.apteczny.vaadin.komponenty.AptecznyMenuBar;
import org.springframework.stereotype.Component;

@Component
@Route("realizacja-recepty")
public class RealizacjaReceptyView extends VerticalLayout {

    public RealizacjaReceptyView() {
        AptecznyMenuBar.dodajMenuBar(this);

        ComboBox<Lek> lekiDoWydaniaCombobox= new ComboBox<>("Lek do wydania", PobieranieLekuZBazyService.pobierzLeki());
        NumberField iloscOpakowan = new NumberField("ilość opakowań", "1");
        iloscOpakowan.setMin(1);
        iloscOpakowan.setMax(100);

        Button przejdzDalejButton = new Button("Przejdź dalej", event -> {
            if (lekiDoWydaniaCombobox.getValue() == null || iloscOpakowan.getValue() == null) {
                Notification.show("Nie podano ilości opakowań lub leku");
                return;
            }
            if (!lekiDoWydaniaCombobox.getValue().isCzyWymagaRecepty()) {
                return; // TODO wyświetlić substancje czynne i przycisk do zakończenia sprzedaży
            }
        });

        add(lekiDoWydaniaCombobox);
        add(iloscOpakowan);
        add(przejdzDalejButton);


    }

}