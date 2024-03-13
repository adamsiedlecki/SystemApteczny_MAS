package net.asiedlecki.system.apteczny;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduktLeczniczy {

    private static final List<ProduktLeczniczy> REJESTR_PRODUKTOW_LECZNICZYCH = new ArrayList<>();

    private final String gtin;
    private final List<SubstancjaCzynna> substancjeCzynne;

    public ProduktLeczniczy(String gtin, List<SubstancjaCzynna> substancjeCzynne) {
        this.gtin = gtin;
        this.substancjeCzynne = substancjeCzynne;
        if (gtin == null || REJESTR_PRODUKTOW_LECZNICZYCH.stream().anyMatch(p -> p.gtin.equals(gtin))) {
            throw new IllegalArgumentException("Lek o podanym GTIN istnieje juz w rejestrze!");
        }
        REJESTR_PRODUKTOW_LECZNICZYCH.add(this);
    }

    public boolean czyProduktNarkotyczny() {
        return substancjeCzynne.stream()
                .anyMatch(s -> s.getNazwaPolska().contains("psylocybina"));
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczePrzetworzoneWApteceWOstatnimCzasie() {
        return Collections.unmodifiableList(REJESTR_PRODUKTOW_LECZNICZYCH);
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczePrzetworzoneWAptece() {
        throw new UnsupportedOperationException("TODO");
    }
}
