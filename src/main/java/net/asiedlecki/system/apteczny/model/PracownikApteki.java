package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.*;
import net.asiedlecki.system.apteczny.db.config.KonwerterEnumSetow;
import net.asiedlecki.system.apteczny.model.enumy.TypPracownikaEnum;
import net.asiedlecki.system.apteczny.model.enumy.WyksztalcenieEnum;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CzasPracy> czasyPracy;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Raport> utworzoneRaporty; // dotyczy kierownika
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SprzedazLeku> sprzedaze; // dotyczy farmaceuty

    public void dodajSprzedaz(SprzedazLeku sprzedazLeku) {
        if (!sprzedaze.contains(sprzedazLeku)) {
            sprzedaze.add(sprzedazLeku);
        }
    }
}
