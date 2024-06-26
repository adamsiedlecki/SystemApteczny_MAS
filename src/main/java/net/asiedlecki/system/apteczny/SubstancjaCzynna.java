package net.asiedlecki.system.apteczny;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubstancjaCzynna {

    @Transient
    private Set<JednostkaChorobowaSubstancjaCzynna> chorobyWKtorychSubstancjaJestPomocna = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwaPolska;

    @Transient
    private List<ProduktLeczniczy> lekiZawierajaceSubstancje = new ArrayList<>();

    public SubstancjaCzynna(String nazwaPolska) {
        this.nazwaPolska = nazwaPolska;
    }

    public void dodajInforamcjeOProdukcieLeczniczymZawierajacymSubstancje(ProduktLeczniczy produktLeczniczy) {
        if (!lekiZawierajaceSubstancje.contains(produktLeczniczy)) {
            lekiZawierajaceSubstancje.add(produktLeczniczy);
            produktLeczniczy.dodajSubstancjeCzynna(this);
        }
    }

    public void usunProduktLeczniczyZawierajacy(ProduktLeczniczy produktLeczniczy) {
        if (lekiZawierajaceSubstancje.contains(produktLeczniczy)) {
            lekiZawierajaceSubstancje.remove(produktLeczniczy);
            produktLeczniczy.usunSubstancjeCzynna(this);
        }
    }

    public List<ProduktLeczniczy> produktyLeczniczeZawierajace() {
        return lekiZawierajaceSubstancje;
    }

    public void dodajChorobyWKtorychMozeBycPomocna(JednostkaChorobowaSubstancjaCzynna jednostkaChorobowaSubstancjaCzynna) {
        chorobyWKtorychSubstancjaJestPomocna.add(jednostkaChorobowaSubstancjaCzynna);
    }
}
