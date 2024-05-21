package net.asiedlecki.system.apteczny.model.magazyn.narzedzi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NozDoKrojeniaTabletek implements Noz{

    @Getter
    private final MaterialWykonania materialWykonania;
    private int liczbaUzyc = 0;

    @Override
    public int pobierzLiczbeUzyc() {
        return liczbaUzyc;
    }

    @Override
    public void zarejestrujUzycie(int ileRazy) {
        liczbaUzyc += ileRazy;
    }

    @Override
    public boolean czyJestZuzyty() {
        return liczbaUzyc >= materialWykonania.getIloscUzycPoKtorymNastepujeZuzycie();
    }
}
