package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.*;
import net.asiedlecki.system.apteczny.db.config.KonwerterEnumSetow;
import net.asiedlecki.system.apteczny.model.enumy.TypPracownikaEnum;
import net.asiedlecki.system.apteczny.model.enumy.WyksztalcenieEnum;

import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PracownikApteki {

    public static WyksztalcenieEnum MINIMALNE_WYKSZTALCENIE = WyksztalcenieEnum.SREDNIE;

    @Getter
    private static Set<PracownikApteki> pracownicyEkstensja = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imie;
    private String nazwisko;
    @Convert(converter = KonwerterEnumSetow.class)
    private EnumSet<TypPracownikaEnum> typPracownikaEnum;
    private Integer lataPracy; // dotyczy farmaceuty
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<CzasPracy> czasyPracy;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Raport> utworzoneRaporty = new ArrayList<>(); // dotyczy kierownika
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SprzedazLeku> sprzedaze; // dotyczy farmaceuty

    public void dodajSprzedaz(SprzedazLeku sprzedazLeku) {
        if (!sprzedaze.contains(sprzedazLeku)) {
            sprzedaze.add(sprzedazLeku);
            sprzedazLeku.getFarmaceuci().forEach(p -> {
                p.dodajSprzedaz(sprzedazLeku);
            });
        }
    }

    public void dodajCzasPracy(CzasPracy czasPracy) {
        if (!czasyPracy.contains(czasPracy)) {
            czasyPracy.add(czasPracy);
            czasPracy.setPracownikApteki(this);
        }
    }
}
