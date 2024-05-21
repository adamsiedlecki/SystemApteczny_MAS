package net.asiedlecki.system.apteczny.model.zadania;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki;

@RequiredArgsConstructor
public abstract class ZadanieWytworzeniaLeku {

    @Getter
    private final int id;
    @Getter
    protected boolean czyFazaWykonana;

    abstract void przydzielPracownika(PlacowkaApteki.PracownikApteki pracownikApteki);

    public abstract ZadanieWytworzeniaLeku pobierzPoprzednieZadanie();

}
