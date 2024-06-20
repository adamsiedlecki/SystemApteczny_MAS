package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IloscSubstancjiCzynnej {

    private static List<IloscSubstancjiCzynnej> ilosciEkstensja = new ArrayList<>();

    @Id
    @GeneratedValue
    private Long id;
    private String jednostka;
    private BigDecimal wartosc;
    @ManyToOne
    private SubstancjaCzynna substancjaCzynna;
    @ManyToOne(fetch = FetchType.EAGER)
    private Lek lek;

    public static void dodajDoEkstensji(IloscSubstancjiCzynnej ilosc) {
        if (!ilosciEkstensja.contains(ilosc)) {
            ilosciEkstensja.add(ilosc);
            ilosc.getLek().dodajIloscSubstancjiCzynnej(ilosc);
        }
    }
}
