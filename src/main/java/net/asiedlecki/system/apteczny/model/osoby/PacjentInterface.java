package net.asiedlecki.system.apteczny.model.osoby;

import net.asiedlecki.system.apteczny.JednostkaChorobowa;

public interface PacjentInterface {
    void dodajChorobe(JednostkaChorobowa jednostkaChorobowa);

    void usunChorobe(JednostkaChorobowa jednostkaChorobowa);

    String podajNazwyChorob();

    java.util.Map<String, JednostkaChorobowa> getChorobyPacjenta();

    String getImie();

    String getNazwisko();
}
