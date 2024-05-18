package net.asiedlecki.system.apteczny.model.osoby;

import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki;

public interface PracownikAptekiInterface {
    PlacowkaApteki pobierzPlacowke();

    String getImie();

    String getNazwisko();
}
