package net.asiedlecki.system.apteczny.model.punkty.sprzedazy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.asiedlecki.system.apteczny.ProduktLeczniczy;
import net.asiedlecki.system.apteczny.model.osoby.PracownikAptekiInterface;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    public class PracownikApteki implements PracownikAptekiInterface {

        public enum PracownikAptekiType {SPRZATACZ("sprzatanie lokalu"), BIUROWY("segregacja dokumentacji"), FARMACEUTA("wydawanie lekow");
            final String opisStanowiskaNaPracujPl;

            PracownikAptekiType(String opis) {
                this.opisStanowiskaNaPracujPl = opis;
            }
        };

        private String imie;
        private String nazwisko;
        private EnumSet<PracownikAptekiType> pracownikKind =  EnumSet.of(PracownikAptekiType.FARMACEUTA);

        public void dodajTypPracownika(PracownikAptekiType pracownikAptekiType) {
            pracownikKind.add(pracownikAptekiType);
        }

        public void usunTypPracownikaa(PracownikAptekiType pracownikAptekiType) {
            pracownikKind.remove(pracownikAptekiType);
        }

        public String sprzatajLokal() {
            if (!pracownikKind.contains(PracownikAptekiType.SPRZATACZ)) {
                throw new IllegalStateException("brak umowy na sprzatanie z tym pracownikiem");
            }
            return "sprzątu - sprzątu";
        }

        public String udzielPoradyFarmaceutycznej(ProduktLeczniczy produktLeczniczy) {
            if (!pracownikKind.contains(PracownikAptekiType.FARMACEUTA)) {
                throw new IllegalStateException("ten pracownik nie jest farmaceuta!");
            }

            if (produktLeczniczy.czyProduktNarkotyczny()) {
                return "nie bierz za duzo bo sie uzaleznisz";
            }
            return "bez ograniczeń";
        }

        private PracownikApteki(String imie, String nazwisko) {
            this.imie = imie;
            this.nazwisko = nazwisko;
            pracownicy.add(this);
        }

        @Override
        public PlacowkaApteki pobierzPlacowke() {
            return PlacowkaApteki.this;
        }
    }

}
