package net.asiedlecki.system.apteczny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Pacjent {

    private final Map<String, JednostkaChorobowa> chorobyPacjenta = new HashMap<>(); // MP2 asocjacja kwalifikowana

    private Long id;
    private String imie;
    private String nazwisko;

    public void dodajChorobe(JednostkaChorobowa jednostkaChorobowa) {
        chorobyPacjenta.put(jednostkaChorobowa.getKodIcd10(), jednostkaChorobowa);
        jednostkaChorobowa.dodajPacjenta(this);
    }

    public void usunChorobe(JednostkaChorobowa jednostkaChorobowa) {
        chorobyPacjenta.remove(jednostkaChorobowa.getKodIcd10());
        jednostkaChorobowa.usunPacjenta(this);
    }

    public String podajNazwyChorob() {
        return chorobyPacjenta.values().stream()
                .map(JednostkaChorobowa::getNazwa)
                .collect(Collectors.joining(", "));
    }
}
