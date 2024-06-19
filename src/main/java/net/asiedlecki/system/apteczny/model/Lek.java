package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Lek {

    @Getter
    private static Set<Lek> lekiEksensja = new HashSet<>();

    @Id
    private String nazwaPolska;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<IloscSubstancjiCzynnej> ilosciSubstancjiCzynnej;
    private BigDecimal cena;
    private int iloscOpakowanWmagazynie;
    private boolean czyWymagaRecepty;

    @Override
    public String toString() {
        return nazwaPolska;
    }

    public String wyswietlSubstancjeCzynne() {
        return ilosciSubstancjiCzynnej.stream()
                .map(s -> s.getSubstancjaCzynna().getNazwaPolska() + " " + (s.getSubstancjaCzynna().getOpisNarkotycznej() != null ? s.getSubstancjaCzynna().getOpisNarkotycznej() : ""))
                .collect(Collectors.joining("\n"));
    }

    public SprzedazLeku sprzedaj(int iloscOpakowan, String idRecepty, List<PracownikApteki> farmceuci) {
        iloscOpakowanWmagazynie-=iloscOpakowan;
        return new SprzedazLeku(null, idRecepty, iloscOpakowan, cena.multiply(BigDecimal.valueOf(iloscOpakowan)), this, farmceuci);
    }
}
