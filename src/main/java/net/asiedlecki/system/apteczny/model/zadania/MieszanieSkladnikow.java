package net.asiedlecki.system.apteczny.model.zadania;

import lombok.extern.slf4j.Slf4j;
import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki;

@Slf4j
public class MieszanieSkladnikow extends ZadanieWytworzeniaLeku{


    private final ZadanieWytworzeniaLeku poprzednieZadanie;

    public MieszanieSkladnikow(ZadanieWytworzeniaLeku zadanieWytworzeniaLeku) {
        super(zadanieWytworzeniaLeku.getId());
        if (!(zadanieWytworzeniaLeku instanceof AnalizaOrazDoborSkladnikow) && !(zadanieWytworzeniaLeku instanceof GotowanieSkladnikow)) {
            throw new IllegalStateException("mieszanie skladnikow musi byc poprzedzone analiza lub gotowaniem");
        }
        this.poprzednieZadanie = zadanieWytworzeniaLeku;
        if (!poprzednieZadanie.czyFazaWykonana) {
            throw new IllegalStateException("nowe zadanie nie moze sie rozpoczac bez zakonczenia poprzaedniego");
        }
    }

    @Override
    public void przydzielPracownika(PlacowkaApteki.PracownikApteki pracownikApteki) {
        log.debug("pracownik {} pracuje nad mieszaniem skladnikow...", pracownikApteki.getImie());
        this.czyFazaWykonana = true;
    }

    @Override
    public ZadanieWytworzeniaLeku pobierzPoprzednieZadanie() {
        return poprzednieZadanie;
    }
}
