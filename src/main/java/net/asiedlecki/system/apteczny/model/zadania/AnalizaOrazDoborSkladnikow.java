package net.asiedlecki.system.apteczny.model.zadania;

import lombok.extern.slf4j.Slf4j;
import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki;

@Slf4j
public class AnalizaOrazDoborSkladnikow extends ZadanieWytworzeniaLeku{


    public AnalizaOrazDoborSkladnikow(int id) {
        super(id);
    }

    @Override
    public void przydzielPracownika(PlacowkaApteki.PracownikApteki pracownikApteki) {
        log.debug("pracownik {} pracuje nad analiza i doborem skladnikow...", pracownikApteki.getImie());
        this.czyFazaWykonana = true;
    }

    @Override
    public ZadanieWytworzeniaLeku pobierzPoprzednieZadanie() {
        return null;
    }
}
