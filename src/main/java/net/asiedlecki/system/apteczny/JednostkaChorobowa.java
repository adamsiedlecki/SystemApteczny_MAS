package net.asiedlecki.system.apteczny;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ToString
@Slf4j
@Getter
@RequiredArgsConstructor
public class JednostkaChorobowa {

    private final Map<Long, Pacjent> pacjenci = new HashMap<>();
    private final Set<JednostkaChorobowaSubstancjaCzynna> substncjeKtoreSaPomocneWLeczeniu = new HashSet<>();

    private final String kodIcd10;
    private final String nazwa;
    private final String nazwaKategorii;

    public void dodajPacjenta(Pacjent pacjent) {
        if (!pacjenci.containsKey(pacjent.getId())) {
            pacjenci.put(pacjent.getId(), pacjent);
            pacjent.dodajChorobe(this); // symetria
        }
    }

    public void usunPacjenta(Pacjent pacjent) {
        if (pacjenci.containsKey(pacjent.getId())) {
            pacjenci.remove(pacjent.getId());
            pacjent.usunChorobe(this); // symetria
        }
    }

    public Set<Pacjent> pobierzPacjentowCierpiacychNaChorobe() {
        return new HashSet<>(pacjenci.values());
    }

    public void dodajPomocnaSubstancjeCzynna(JednostkaChorobowaSubstancjaCzynna jednostkaChorobowaSubstancjaCzynna) {
        substncjeKtoreSaPomocneWLeczeniu.add(jednostkaChorobowaSubstancjaCzynna);
    }
}
