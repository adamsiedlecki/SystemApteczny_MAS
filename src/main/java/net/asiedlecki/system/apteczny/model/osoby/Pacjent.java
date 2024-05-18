package net.asiedlecki.system.apteczny.model.osoby;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.asiedlecki.system.apteczny.JednostkaChorobowa;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Pacjent implements PacjentInterface {

    private final Map<String, JednostkaChorobowa> chorobyPacjenta = new HashMap<>(); // MP2 asocjacja kwalifikowana

    private Long id;
    private String imie;
    private String nazwisko;

    @Override
    public void dodajChorobe(JednostkaChorobowa jednostkaChorobowa) {
        if (!chorobyPacjenta.containsKey(jednostkaChorobowa.getKodIcd10())) {
            chorobyPacjenta.put(jednostkaChorobowa.getKodIcd10(), jednostkaChorobowa); // symetria
            jednostkaChorobowa.dodajPacjenta(this);
        }
    }

    @Override
    public void usunChorobe(JednostkaChorobowa jednostkaChorobowa) {
        if (chorobyPacjenta.containsKey(jednostkaChorobowa.getKodIcd10())) { // symetria
            chorobyPacjenta.remove(jednostkaChorobowa.getKodIcd10());
            jednostkaChorobowa.usunPacjenta(this);
        }
    }

    @Override
    public String podajNazwyChorob() {
        return chorobyPacjenta.values().stream()
                .map(JednostkaChorobowa::getNazwa)
                .collect(Collectors.joining(", "));
    }
}
