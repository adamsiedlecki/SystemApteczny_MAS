package net.asiedlecki.system.apteczny.vaadin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import net.asiedlecki.system.apteczny.serwisy.KontrolaEkstensji;
import net.asiedlecki.system.apteczny.vaadin.komponenty.AptecznyMenuBar;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        AptecznyMenuBar.dodajMenuBar(this);
        add(new Text("Witaj w systemie aptecznym!"));

        Button zapisDoBazdyDanychButton = new Button("zapisywanie do bazy danych (niesamowicie intuicyjne!)");
        zapisDoBazdyDanychButton.addClickListener(e -> {
            KontrolaEkstensji.zapiszDoBazyDanych();
            Notification.show("Zapis do BD sie powiodl - można robić przerwę w dostawie prądu :)");
        });

        add(zapisDoBazdyDanychButton);
    }

}