package net.asiedlecki.system.apteczny.model.osoby;

import net.asiedlecki.system.apteczny.JednostkaChorobowa;
import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki;

import java.util.Map;

public class PacjentBedacyPracownikiemApteki implements PacjentInterface, PracownikAptekiInterface{

    private final Pacjent pacjent;
    private final PlacowkaApteki.PracownikApteki pracownikApteki;

    public PacjentBedacyPracownikiemApteki(Pacjent pacjent, PlacowkaApteki.PracownikApteki pracownikApteki) {
        this.pacjent = pacjent;
        this.pracownikApteki = pracownikApteki;

        if (!pacjent.getImie().equals(pracownikApteki.getImie()) || !pacjent.getNazwisko().equals(pracownikApteki.getNazwisko())) {
            throw new IllegalArgumentException("Imiona lub nazwiska sa niezgodne!");
        }
    }

    @Override
    public void dodajChorobe(JednostkaChorobowa jednostkaChorobowa) {
        pacjent.dodajChorobe(jednostkaChorobowa);
    }

    @Override
    public void usunChorobe(JednostkaChorobowa jednostkaChorobowa) {
        pacjent.usunChorobe(jednostkaChorobowa);
    }

    @Override
    public String podajNazwyChorob() {
        return pacjent.podajNazwyChorob();
    }

    @Override
    public Map<String, JednostkaChorobowa> getChorobyPacjenta() {
        return pacjent.getChorobyPacjenta();
    }

    @Override
    public PlacowkaApteki pobierzPlacowke() {
        return pracownikApteki.pobierzPlacowke();
    }

    @Override
    public String getImie() {
        return pacjent.getImie();
    }

    @Override
    public String getNazwisko() {
        return pacjent.getNazwisko();
    }
}
