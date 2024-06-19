package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SprzedazLeku {

    private static Set<SprzedazLeku> sprzedazeEkstensja = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idRecepty; // moze byc bez recepty
    private int iloscOpakowan;
    private BigDecimal cenaCalosci;
    @ManyToOne
    private Lek lek;
    @ManyToMany
    private List<PracownikApteki> farmaceuci;

    public SprzedazLeku(Long id, String idRecepty, int iloscOpakowan, BigDecimal cenaCalosci, Lek lek, List<PracownikApteki> farmaceuci) {
        this.id = id;
        this.idRecepty = idRecepty;
        this.iloscOpakowan = iloscOpakowan;
        this.cenaCalosci = cenaCalosci;
        this.lek = lek;
        setFarmaceuci(farmaceuci);
    }

    public void setFarmaceuci(List<PracownikApteki> farmaceuci) {
        this.farmaceuci = farmaceuci;
        farmaceuci.forEach(p -> p.dodajSprzedaz(this));
    }

    public static void dodajSprzedaz(SprzedazLeku sprzedazLeku) {
        if (!sprzedazeEkstensja.contains(sprzedazLeku)) {
            sprzedazeEkstensja.add(sprzedazLeku);
            sprzedazLeku.getFarmaceuci().forEach(farmaceuta -> farmaceuta.dodajSprzedaz(sprzedazLeku));
        }
    }

    @Override
    public String toString() {
        return "SprzedazLeku{" +
                "iloscOpakowan=" + iloscOpakowan +
                ", lek=" + lek.getNazwaPolska() +
                '}';
    }
}
