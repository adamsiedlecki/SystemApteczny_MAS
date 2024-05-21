package net.asiedlecki.system.apteczny.model.zadania;

import lombok.extern.slf4j.Slf4j;
import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PakowanieWytworu extends ZadanieWytworzeniaLeku{

    private final ZadanieWytworzeniaLeku poprzednieZadanie;

    public PakowanieWytworu(ZadanieWytworzeniaLeku zadanieWytworzeniaLeku) {
        super(zadanieWytworzeniaLeku.getId());
        if (!(zadanieWytworzeniaLeku instanceof MieszanieSkladnikow) && !(zadanieWytworzeniaLeku instanceof GotowanieSkladnikow)) {
            throw new IllegalStateException("mieszanie skladnikow musi byc poprzedzone analiza");
        }
        this.poprzednieZadanie = zadanieWytworzeniaLeku;
        if (!poprzednieZadanie.czyFazaWykonana) {
            throw new IllegalStateException("nowe zadanie nie moze sie rozpoczac bez zakonczenia poprzaedniego");
        }
    }

    @Override
    public void przydzielPracownika(PlacowkaApteki.PracownikApteki pracownikApteki) {
        log.debug("pracownik {} pracuje nad pakowaniem...", pracownikApteki.getImie());
        this.czyFazaWykonana = true;
    }

    public String utworzRaportZeWszystkichFaz() {
        List<String> list = new ArrayList<>();
        if (czyFazaWykonana) {
            list.add(this.getClass().getSimpleName());
        }
        ZadanieWytworzeniaLeku poprzednie = this;
        while((poprzednie = poprzednie.pobierzPoprzednieZadanie()) != null) {
            list.add(poprzednie.getClass().getSimpleName());
        }
        return String.join(" -> ", list.reversed());
    }

    @Override
    public ZadanieWytworzeniaLeku pobierzPoprzednieZadanie() {
        return poprzednieZadanie;
    }
}
