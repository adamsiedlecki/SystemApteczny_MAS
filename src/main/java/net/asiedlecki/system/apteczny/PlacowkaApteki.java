package net.asiedlecki.system.apteczny;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@RequiredArgsConstructor
public class PlacowkaApteki {

    private final Long id;
    private final Set<PracownikApteki> pracownicy = new HashSet<>();

    public PracownikApteki dodajPracownika(String imie, String nazwisko) {
        return new PracownikApteki(imie, nazwisko);
    }

    @ToString
    @Getter
    public class PracownikApteki {
        private String imie;
        private String nazwisko;

        private PracownikApteki(String imie, String nazwisko) {
            this.imie = imie;
            this.nazwisko = nazwisko;
            pracownicy.add(this);
        }

        public PlacowkaApteki pobierzPlacowke() {
            return PlacowkaApteki.this;
        }
    }

}
