package net.asiedlecki.system.apteczny.model.magazyn.narzedzi;

public interface Noz {

    int pobierzLiczbeUzyc();

    void zarejestrujUzycie(int ileRazy);

    boolean czyJestZuzyty();
}
