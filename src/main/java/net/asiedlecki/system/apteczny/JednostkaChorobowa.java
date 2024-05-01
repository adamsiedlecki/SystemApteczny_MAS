package net.asiedlecki.system.apteczny;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class JednostkaChorobowa {

    private final Map<Long, Pacjent> pacjenci = new HashMap<>();

    private final String kodIcd10;
    private final String nazwa;
    private final String nazwaKategorii;

    public void dodajPacjenta(Pacjent pacjent) {
        pacjenci.put(pacjent.getId(), pacjent);
    }

    public void usunPacjenta(Pacjent pacjent) {
        pacjenci.remove(pacjent.getId());
    }

    public Set<Pacjent> pobierzPacjentowCierpiacychNaChorobe() {
        return new HashSet<>(pacjenci.values());
    }
}
