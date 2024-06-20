package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SubstancjaCzynna {

    private static final List<SubstancjaCzynna> substancjeEkstensja = new ArrayList<>();

    @Id
    private String nazwaPolska;
    private String opisNarkotycznej;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<IloscSubstancjiCzynnej> iloscSubstancjiCzynnejList;

    @Transient
    public boolean czyNarkotyczna() {
        return opisNarkotycznej != null;
    }

    public static void dodajDoEkstensji(SubstancjaCzynna substancjaCzynna) {
        if (!substancjeEkstensja.contains(substancjaCzynna)) {
            substancjeEkstensja.add(substancjaCzynna);
            substancjaCzynna.getIloscSubstancjiCzynnejList().forEach(i -> {
                i.setSubstancjaCzynna(substancjaCzynna);
            });
        }
    }
}
