package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.model.Lek;
import net.asiedlecki.system.apteczny.serwisy.LekiService;
import net.asiedlecki.system.apteczny.serwisy.system.panstwowy.OdpowiedzSystemuPanstwowego;
import net.asiedlecki.system.apteczny.serwisy.system.panstwowy.SystemPanstwowyService;
import net.asiedlecki.system.apteczny.vaadin.komponenty.AptecznyMenuBar;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
@Route("realizacja-recepty")
public class RealizacjaReceptyView extends VerticalLayout {

    private static final String ZAKONCZ_SPRZEDAZ = "zakończ sprzedaż";

    public RealizacjaReceptyView() {
        AptecznyMenuBar.dodajMenuBar(this);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout verticalLayout = new VerticalLayout();
        VerticalLayout formVerticalLayout = new VerticalLayout();

        ComboBox<Lek> lekiDoWydaniaCombobox = new ComboBox<>("Lek do wydania");
        lekiDoWydaniaCombobox.setItems(LekiService.pobierzLeki());
        NumberField iloscOpakowan = new NumberField("ilość opakowań");
        TextField idReceptyField = new TextField("id recepty");
        iloscOpakowan.setMin(1);
        iloscOpakowan.setMax(100);
        iloscOpakowan.setValue(1.0);

        lekiDoWydaniaCombobox.addValueChangeListener(event -> {
            formVerticalLayout.remove(idReceptyField);
        });

        Button zakonczSprzedazButton = new Button(ZAKONCZ_SPRZEDAZ, event -> {
            Notification.show("Zakończono sprzedaż poprawnie, zostaniesz przeniesiony za 3s");

            Page page = getUI().get().getPage();
            page.executeJs("setTimeout(function() { window.location.href = '/'; }, 3000);");

        });
        zakonczSprzedazButton.setEnabled(false);

        Button przejdzDalejButton = new Button("Przejdź dalej", event -> {
            if (lekiDoWydaniaCombobox.getValue() == null || iloscOpakowan.getValue() == null) {
                Notification.show("Nie podano ilości opakowań lub leku");
                return;
            }
            if (iloscOpakowan.getValue() > lekiDoWydaniaCombobox.getValue().getIloscOpakowanWmagazynie()) {
                Notification.show("Nie ma takiej ilosci leku w magazynie");
                return;
            }
            if (!lekiDoWydaniaCombobox.getValue().isCzyWymagaRecepty()) {
                horizontalLayout.add(new Text(lekiDoWydaniaCombobox.getValue().pobierzSusbtancjeZOpisami()));
                zakonczSprzedazButton.setEnabled(true);
                event.getSource().setEnabled(false);
                return;
            } else {
                if (formVerticalLayout.getChildren().noneMatch(c -> c.equals(idReceptyField))) {
                    formVerticalLayout.add(idReceptyField);
                    event.getSource().setText("wykonaj zapytanie do systemu państwowego");
                    return;
                } else {
                    OdpowiedzSystemuPanstwowego odpowiedz = SystemPanstwowyService.pobierz(idReceptyField.getValue());
                    if (odpowiedz.isCzyPozytywna()) {
                        formVerticalLayout.add(new Text("System państwowy zweryfikował recepę pozytywnie ✔"));
                        horizontalLayout.add(new Text(lekiDoWydaniaCombobox.getValue().pobierzSusbtancjeZOpisami()));
                        zakonczSprzedazButton.setEnabled(true);
                        event.getSource().setEnabled(false);
                    } else {
                        formVerticalLayout.add(new Text("System państwowy zweryfikował recepę nagetywnie ❌"));
                        formVerticalLayout.add(new Text(odpowiedz.getWyjasnienie()));
                        event.getSource().setEnabled(false);
                    }
                }
            }
        });

        formVerticalLayout.add(lekiDoWydaniaCombobox);
        formVerticalLayout.add(iloscOpakowan);

        verticalLayout.add(formVerticalLayout);
        verticalLayout.add(przejdzDalejButton);
        verticalLayout.add(zakonczSprzedazButton);

        horizontalLayout.add(verticalLayout);
        add(horizontalLayout);


    }

}